package com.hdh.redpacket.system.constant;

public enum GameStatusTypeEnum {

    START_GAME("start_game","游戏开始"),

    STOP_INPUT("stop_input","停止投注"),

    OPEN_AWARD("open_award","开奖");

    private String code;

    private String value;

    private GameStatusTypeEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
