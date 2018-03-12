package com.hdh.redpacket.game.service;

import com.alibaba.fastjson.JSON;
import com.hdh.redpacket.core.exception.SysException;
import com.hdh.redpacket.core.utils.RandomUtils;
import com.hdh.redpacket.game.exception.GameException;
import com.hdh.redpacket.game.mapper.GamePlayDetailMapper;
import com.hdh.redpacket.game.mapper.GamePlayMapper;
import com.hdh.redpacket.game.model.GamePlay;
import com.hdh.redpacket.game.model.GamePlayDetail;
import com.hdh.redpacket.system.constant.DictEnum;
import com.hdh.redpacket.system.constant.GameStatusEnum;
import com.hdh.redpacket.system.model.ConfigDic;
import com.hdh.redpacket.system.service.ConfigDicService;
import com.hdh.redpacket.system.socket.WebSocket;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 游戏主要业务类
 */
@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    @Autowired
    private ConfigDicService configDicService;

    @Autowired
    private GamePlayMapper gamePlayMapper;

    @Autowired
    private GamePlayDetailMapper gamePlayDetailMapper;

    /**
     * 获取可投的钻石列表
     * @return
     */
    public List<ConfigDic> getDiamondList(){
        return configDicService.getDicsByCode(DictEnum.DIAMOND_LIST.getCode());
    }

    /**
     * 获取当前游戏场次的信息
     * @return
     */
    public GamePlay getCurrentGameMsg(){
        // 获取正在投入或者等待开奖的场次信息(正常情况只有一条),多与一条则报错
        List<Integer> status = Arrays.asList(GameStatusEnum.PLAYING.getCode(),GameStatusEnum.WAIT_OPEN.getCode());
        List<GamePlay> gamePlays = gamePlayMapper.selectByStatus(status);
        if(gamePlays != null && gamePlays.size() > 1){
            throw SysException.SYS_ERROR;
        }
        return gamePlays.get(0);
    }

    /**
     * 投入钻石
     * @param playNo
     * @param userId
     * @param diamondsId
     */
    @Transactional
    public void addDiamond(String playNo, String userId, Long diamondsId){
        if(StringUtils.isBlank(userId) || diamondsId == null || StringUtils.isBlank(playNo)){
            throw GameException.PARAMS_ERROR;
        }

        ConfigDic configDic = configDicService.getDicById(diamondsId);
        if(configDic == null){
            throw SysException.SYS_ERROR;
        }

        GamePlay gamePlay = gamePlayMapper.selectByPlayNo(playNo);
        if(gamePlay == null){
            throw SysException.SYS_ERROR;
        }

        //  更新场次表
        gamePlay.setTotalDiamond(gamePlay.getTotalDiamond().add(new BigDecimal(configDic.getDicValue())));
        gamePlayMapper.updateByPlayNoSelective(gamePlay);

        // 更新场次明细表
        GamePlayDetail gamePlayDetail = new GamePlayDetail();
        gamePlayDetail.setCreateTime(new Date());
        gamePlayDetail.setDiamond(new BigDecimal(configDic.getDicValue()));
        gamePlayDetail.setNum(1);
        gamePlayDetail.setPlayNo(playNo);
        gamePlayDetail.setUserId(userId);
        gamePlayDetailMapper.insertOrUpdate(gamePlayDetail);
    }

    /**
     * 开始游戏定时任务
     */
    @Scheduled(cron = "0 1 * * * *")
    private void startGame(){
        // TODO 判断当前时间是否在允许游戏进行时间段里
        GamePlay gamePlay = new GamePlay();
        gamePlay.setTotalDiamond(BigDecimal.ZERO);
        gamePlay.setStatus(GameStatusEnum.PLAYING.getCode());
        gamePlay.setCreatTime(new Date());
        gamePlay.setTimes(new BigDecimal("0.5"));
        gamePlay.setPlayNo(RandomUtils.getPlayNoByTime());
        gamePlayMapper.insert(gamePlay);
    }

    /**
     * 停止投入定时任务
     */
    @Scheduled(cron = "0 30 * * * *")
    private void stopInputDiamond(){
        GamePlay gamePlay = getCurrentGameMsg();
        if(gamePlay == null){
            logger.error("没找到进行中游戏场次，游戏场次有误");
        }

        gamePlay.setStatus(GameStatusEnum.WAIT_OPEN.getCode());
        gamePlay.setOverInputTime(new Date());
        gamePlayMapper.updateByPlayNoSelective(gamePlay);

        // 把游戏停止投入状态推送到前端
        String stopInput = JSON.toJSONString(gamePlay);
        CopyOnWriteArraySet<WebSocket> webSockets = WebSocket.webSocketSet;
        for(WebSocket webSocket : webSockets){
            try {
                webSocket.sendMessage(stopInput);
            } catch (IOException e) {
                logger.error("socket通讯失败，停止投入信息推送失败",e);
            }
        }
    }

    /**
     * 开奖定时任务
     */
    @Scheduled(cron = "0 58 * * * *")
    private void openAward(){

    }
}
