package com.hdh.redpacket.game.dto;

import com.hdh.redpacket.game.model.GamePlay;

public class GamePlayDto extends GamePlay{

    // 胜者姓名
    private String winName;

    public String getWinName() {
        return winName;
    }

    public void setWinName(String winName) {
        this.winName = winName;
    }
}
