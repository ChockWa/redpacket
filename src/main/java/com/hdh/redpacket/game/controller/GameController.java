package com.hdh.redpacket.game.controller;

import com.hdh.redpacket.common.service.UserInfo;
import com.hdh.redpacket.core.annotation.MustLogin;
import com.hdh.redpacket.core.model.Result;
import com.hdh.redpacket.game.model.GamePlay;
import com.hdh.redpacket.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        gameService.addDiamond(playNo,UserInfo.getUser().getId(),diamondsId);
        return Result.SUCCESS();
    }

    /**
     * 获取当前场次的信息
     * @return
     */
    @RequestMapping(value = "/getCurrentGameMsg")
    public Result getCurrentGameMsg(){
        GamePlay gamePlay = gameService.getCurrentGameMsg();
        return Result.SUCCESS().setData(gamePlay);
    }
}
