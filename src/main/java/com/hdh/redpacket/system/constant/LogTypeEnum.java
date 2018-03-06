package com.hdh.redpacket.system.constant;

public enum LogTypeEnum {

    REQUEST_LOG("request_log","请求日志");

    private String type;

    private String dec;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    private LogTypeEnum(String type, String dec) {
        this.type = type;
        this.dec = dec;
    }
}
