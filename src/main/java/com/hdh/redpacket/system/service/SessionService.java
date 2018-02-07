package com.hdh.redpacket.system.service;

import com.hdh.redpacket.core.cache.RedisService;
import com.hdh.redpacket.core.utils.RandomUtils;
import com.hdh.redpacket.core.utils.UuidUtil;
import com.hdh.redpacket.system.dto.SessionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * session会话相关业务逻辑
 */
@Service
public class SessionService {

    private final static long SESSION_EXPIRE_TIME = 30 * 24 * 60 * 60; // 会话失效时间.
    private final static long SESSION_RENEW_TIME = 15 * 24 * 60 * 60; // 会话失效时间.

    @Autowired
    private RedisService redisService;

    /**
     * 获取session
     * @param sessionKey
     * @return
     */
    public SessionDto getSession(String sessionKey){
        return (SessionDto) redisService.get(sessionKey);
    }

    /**
     * 生成session
     * @return
     */
    public SessionDto genSession() {
        SessionDto session = new SessionDto();
        session.setSessionKey(newSessionKey());
        session.setSecretKey(newSessionSecret());
        session.setTimestamp(getCurrentTimestamp());
        session.setCreateTime(getCurrentTime());
        session.setExpireTime(getExpireTime());
        session.setRenewTime(getRenewTime());
        return saveSessionSecret(session) ? session : null;
    }

    private boolean saveSessionSecret(SessionDto session) {
        redisService.set(session.getSessionKey(),session,(int)SESSION_EXPIRE_TIME);
        return true;
    }

    private Date getRenewTime() {
        return new Date(System.currentTimeMillis() + (SESSION_RENEW_TIME * 1000));
    }

    private Date getExpireTime() {
        return new Date(System.currentTimeMillis() + (SESSION_EXPIRE_TIME * 1000));
    }

    private Date getCurrentTime() {
        return new Date();
    }

    private static String newSessionKey() {
        return UuidUtil.genUuidNoLine();
    }

    private static String newSessionSecret() {
        return new RandomUtils(RandomUtils.AllLetter).generate(64);
    }

    private static long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }
}
