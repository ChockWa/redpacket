package com.hdh.redpacket.system.dto;

import java.util.Date;

public class SessionDto {

    /** 会话key */
    public String sessionKey;
    /** 会话中时密钥 */
    public String secretKey;
    /** 会话创建时间 */
    public Date createTime;
    /** 续约时间。会话再这个时间点之后需要续约 */
    private Date renewTime;
    /** 会话过期时间。会话再这个时间点之后过期 */
    public Date expireTime;
    /** 服务器时间戳。单位：秒 */
    public Long timestamp;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRenewTime() {
        return renewTime;
    }

    public void setRenewTime(Date renewTime) {
        this.renewTime = renewTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
