package com.hdh.redpacket.game.dto;

import com.hdh.redpacket.game.model.GamePlay;

import java.math.BigDecimal;

public class GamePlayDto extends GamePlay{

    // 胜者姓名
    private String winName;

    // 玩家本场次投入的
    private BigDecimal playerDiamond;

    public BigDecimal getPlayerDiamond() {
        return playerDiamond;
    }

    public void setPlayerDiamond(BigDecimal playerDiamond) {
        this.playerDiamond = playerDiamond;
    }

    public String getWinName() {
        return winName;
    }

    public void setWinName(String winName) {
        this.winName = winName;
    }
}
