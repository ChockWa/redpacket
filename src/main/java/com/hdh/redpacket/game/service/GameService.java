package com.hdh.redpacket.game.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hdh.redpacket.core.exception.SysException;
import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.core.utils.BeanUtils;
import com.hdh.redpacket.core.utils.RandomUtils;
import com.hdh.redpacket.game.dto.GamePlayDto;
import com.hdh.redpacket.game.exception.GameException;
import com.hdh.redpacket.game.mapper.GamePlayDetailMapper;
import com.hdh.redpacket.game.mapper.GamePlayMapper;
import com.hdh.redpacket.game.model.GamePlay;
import com.hdh.redpacket.game.model.GamePlayDetail;
import com.hdh.redpacket.system.constant.DictEnum;
import com.hdh.redpacket.system.constant.GameStatusEnum;
import com.hdh.redpacket.system.constant.PropertyTypeEnum;
import com.hdh.redpacket.system.model.ConfigDic;
import com.hdh.redpacket.system.service.ConfigDicService;
import com.hdh.redpacket.system.socket.WebSocket;
import com.hdh.redpacket.user.model.User;
import com.hdh.redpacket.user.model.UserProperty;
import com.hdh.redpacket.user.service.PropertyLogService;
import com.hdh.redpacket.user.service.UserPropertyService;
import com.hdh.redpacket.user.service.UserService;
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
import java.util.stream.Collectors;

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

    @Autowired
    private UserPropertyService userPropertyService;

    @Autowired
    private PropertyLogService propertyLogService;

    @Autowired
    private CaculateService caculateService;

    @Autowired
    private UserService userService;

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
        // 获取正在投入或者等待开奖的场次信息(正常情况只有一条),多于一条则报错
        List<Integer> status = Arrays.asList(GameStatusEnum.PLAYING.getCode(),GameStatusEnum.WAIT_OPEN.getCode());
        List<GamePlay> gamePlays = gamePlayMapper.selectByStatus(status);
        if(gamePlays != null && gamePlays.size() < 1){
            throw GameException.GAME_ISNOT_START;
        }
        return gamePlays.get(0);
    }

    public GamePlay getPlayByStatus(Integer status){
        if(status == null){
            return null;
        }
        return gamePlayMapper.selectByOneStatus(status);
    }

    public List<GamePlay> getPlayByStatus(List<Integer> status){
        if(status == null || status.size() < 1){
            return new ArrayList<>();
        }
        return gamePlayMapper.selectByStatus(status);
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

        // 判断玩家钻石够不够
        UserProperty userProperty = userPropertyService.getUserProperties(userId);
        if(userProperty == null || userProperty.getDiamond() < Integer.valueOf(configDic.getDicValue())){
            throw GameException.DIAMOND_IS_NOT_ENOUGH;
        }

        GamePlay gamePlay = gamePlayMapper.selectByPlayNo(playNo);
        if(gamePlay == null){
            throw SysException.SYS_ERROR;
        }

        // 插入钻石减少的记录
        propertyLogService.addPropertyLog(userId, PropertyTypeEnum.DIAMOND.name(),new BigDecimal(userProperty.getDiamond()),
                new BigDecimal(userProperty.getDiamond()-Integer.valueOf(configDic.getDicValue())),2,new Date());

        // 减少用户钻石
        userProperty.setDiamond(userProperty.getDiamond() - Integer.valueOf(configDic.getDicValue()));
        userPropertyService.updateUserProperty(userProperty);

        // 更新场次表
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
    public void startGame(){
        List<GamePlay> gamePlays = getPlayByStatus(Arrays.asList(GameStatusEnum.PLAYING.getCode(),GameStatusEnum.WAIT_OPEN.getCode()));
        if(gamePlays != null && gamePlays.size() > 0){
            logger.error("有游戏正在进行中");
            pushGamePlayMsg(JSON.toJSONString(Result.SUCCESS().setData(gamePlays.get(0))));
            return;
        }
        // TODO 判断当前时间是否在允许游戏进行时间段里
        GamePlay gamePlay = new GamePlay();
        gamePlay.setTotalDiamond(BigDecimal.ZERO);
        gamePlay.setStatus(GameStatusEnum.PLAYING.getCode());
        gamePlay.setCreateTime(new Date());
        gamePlay.setTimes(new BigDecimal("0.5"));
        gamePlay.setPlayNo(RandomUtils.getPlayNoByTime());
        gamePlayMapper.insert(gamePlay);

        // 把游戏开始状态推送到前端
        pushGamePlayMsg(JSON.toJSONString(Result.SUCCESS().setData(gamePlay)));
    }

    /**
     * 停止投入定时任务
     */
    public void stopInputDiamond(){
        GamePlay gamePlay = getPlayByStatus(GameStatusEnum.PLAYING.getCode());
        if(gamePlay == null){
            logger.error("没找到进行中游戏场次，游戏场次有误");
            return;
        }

        gamePlay.setStatus(GameStatusEnum.WAIT_OPEN.getCode());
        gamePlay.setOverInputTime(new Date());
        gamePlayMapper.updateByPlayNoSelective(gamePlay);

        // 把游戏停止投入状态推送到前端
        pushGamePlayMsg(JSON.toJSONString(Result.SUCCESS().setData(gamePlay)));
    }

    /**
     * 开奖定时任务
     */
    @Transactional
    public void openAward(){
        GamePlay gamePlay = getPlayByStatus(GameStatusEnum.WAIT_OPEN.getCode());
        if(gamePlay == null){
            logger.error("没找到等待开奖的场次，游戏场次有误");
            return;
        }
        List<GamePlayDetail> gamePlayDetails = gamePlayDetailMapper.selectByPlayNo(gamePlay.getPlayNo());
        if(gamePlayDetails == null || gamePlayDetails.size() < 1){
            logger.info("没有玩家参与这场游戏");
            gamePlay.setStatus(GameStatusEnum.OVER.getCode());
            gamePlay.setOverTime(new Date());
            gamePlayMapper.updateByPlayNoSelective(gamePlay);
            pushGamePlayMsg(JSON.toJSONString(Result.FAIL(GameException.NO_PLAYER_ERROR.getCode(),GameException.NO_PLAYER_ERROR.getMsg())));
            return;
        }

        List<String> userIds = gamePlayDetails.stream().map(m -> m.getUserId()).collect(Collectors.toList());
        // 获取参与游戏的用户的属性
        List<UserProperty> userProperties = userPropertyService.selectUserProByUserIds(userIds);
        // 获得胜出者的用户id
        String winnerUserId = caculateService.getWinner(caculateService.getUserWinProbability(userProperties));
        // 如果用户id为空，则表明这场游戏没有胜出者
        User user = userService.getUserById(winnerUserId);
        if(user == null){
            logger.info("没有玩家胜出这场游戏");
            gamePlay.setStatus(GameStatusEnum.OVER.getCode());
            gamePlay.setOverTime(new Date());
            gamePlay.setWinAmount(BigDecimal.ZERO);
            gamePlay.setWinUserId("0");
            gamePlayMapper.updateByPlayNoSelective(gamePlay);
            pushGamePlayMsg(JSON.toJSONString(Result.FAIL(GameException.NO_WINNER_ERROR.getCode(),GameException.NO_WINNER_ERROR.getMsg())));
            return;
        }

        // 胜出者增加相应的钻石数
        GamePlayDetail winnerDetail = gamePlayDetails.stream().filter(f -> winnerUserId.equals(f.getUserId())).collect(Collectors.toList()).get(0);
        UserProperty winnerProperty = userProperties.stream().filter(f -> winnerUserId.equals(f.getUserId())).collect(Collectors.toList()).get(0);
        BigDecimal newDiamond = winnerDetail.getDiamond().multiply(gamePlay.getTimes()).setScale(BigDecimal.ROUND_DOWN);

        // 记录玩家增加的钻石数
        propertyLogService.addPropertyLog(winnerUserId,PropertyTypeEnum.DIAMOND.name(),new BigDecimal(winnerProperty.getDiamond()),
                newDiamond,1,new Date());

        // 更新玩家属性表
        winnerProperty.setDiamond(newDiamond.intValue());
        winnerProperty.setWinPlays(winnerProperty.getWinPlays() + 1);
        List<GamePlay> winPlays = gamePlayMapper.selectByWinUserId(winnerUserId);
        List<GamePlayDetail> joinPlays = gamePlayDetailMapper.selectByUserId(winnerUserId);
        winnerProperty.setWinProbability(new BigDecimal(winPlays.size()+1).divide(new BigDecimal(joinPlays.size())));
        userPropertyService.updateUserProperty(winnerProperty);

        //更新场次表信息
        gamePlay.setStatus(GameStatusEnum.OVER.getCode());
        gamePlay.setWinUserId(winnerUserId);
        gamePlay.setWinAmount(newDiamond);
        gamePlay.setOverTime(new Date());
        gamePlayMapper.updateByPlayNoSelective(gamePlay);

        GamePlayDto gamePlayDto = BeanUtils.copyToNewBean(gamePlay,GamePlayDto.class);
        gamePlayDto.setWinName(user.getName());

        // 把游戏结果推送到前端
        pushGamePlayMsg(JSON.toJSONString(Result.SUCCESS().setData(gamePlayDto)));

    }

    private void pushGamePlayMsg(String message){
        CopyOnWriteArraySet<WebSocket> webSockets = WebSocket.webSocketSet;
        for(WebSocket webSocket : webSockets){
            try {
                webSocket.sendMessage(message);
            } catch (IOException e) {
                logger.error("socket通讯失败，游戏结果信息推送失败:{}",message,e);
            }
        }
    }
}
