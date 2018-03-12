package com.hdh.redpacket.system.constant;

public enum GameStatusEnum {

    WAIT_START(1,"等待开始"),
    PLAYING(2,"投入进行中"),
    WAIT_OPEN(3,"等待开奖"),
    OVER(4,"已结束");

    private Integer code;

    private String desc;

    private GameStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
