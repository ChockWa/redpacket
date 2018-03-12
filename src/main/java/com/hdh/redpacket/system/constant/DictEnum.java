package com.hdh.redpacket.system.constant;

public enum  DictEnum {

    AMOUNT_DIAMOND("amount_diamond","金额与钻石的比例"),

    INVITE_ADD_PROD("invite_add_prod","邀请一个人增加的概率"),

    DIAMOND_LIST("diamonds","投入钻石列表");

    private String code;

    private String value;

    private DictEnum(String code, String value) {
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
