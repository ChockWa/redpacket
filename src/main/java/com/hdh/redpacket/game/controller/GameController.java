package com.hdh.redpacket.game.controller;

import com.hdh.redpacket.common.service.UserInfo;
import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.core.utils.DictUtils;
import com.hdh.redpacket.game.dto.GamePlayDto;
import com.hdh.redpacket.game.model.GamePlay;
import com.hdh.redpacket.game.service.GameService;
import com.hdh.redpacket.system.constant.DictEnum;
import com.hdh.redpacket.system.model.ConfigDic;
import com.hdh.redpacket.system.socket.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
@MustLogin(false)
public class GameController {

    @Autowired
    private GameService gameService;

    /**
     * 投钻石
     * @param playNo
     * @param diamondsId
     * @return
     */
    @RequestMapping(value = "/inputDiamond")
    public Result inputDiamond(String playNo,Long diamondsId){
        gameService.addDiamond(playNo,UserInfo.getUser() == null?"":UserInfo.getUser().getId(),diamondsId);
        return Result.SUCCESS();
    }

    /**
     * 获取当前场次的信息
     * @return
     */
    @RequestMapping(value = "/getCurrentGameMsg")
    public Result getCurrentGameMsg(){
        GamePlayDto gamePlayDto = gameService.getCurrentGameMsg(UserInfo.getUser()==null?null:UserInfo.getUser().getId());
        return Result.SUCCESS().setData(gamePlayDto);
    }

    /**
     * 获取可投入钻石列表
     * @return
     */
    @RequestMapping(value = "/getDiamondList")
    public Result getDiamondList(){
        List<ConfigDic> diamondList = DictUtils.getDicList(DictEnum.DIAMOND_LIST.getCode());
        return Result.SUCCESS().setData("diamondList",diamondList);
    }
}
