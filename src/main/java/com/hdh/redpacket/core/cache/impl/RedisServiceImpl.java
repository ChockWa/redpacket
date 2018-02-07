package com.hdh.redpacket.core.cache.impl;

import com.hdh.redpacket.core.cache.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis相关操作
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void set(String key, Object value, int timeoutSecond) {
        redisTemplate.opsForValue().set(key, value, timeoutSecond, TimeUnit.SECONDS);
    }

    @Override
    public void expire(String key, int timeoutSecond) {
        redisTemplate.expire(key, timeoutSecond, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    @Override
    public void del(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public long inc(String key) {
        return incBy(key, 1);
    }

    @Override
    public long incBy(String key, long step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 根据前缀删除所有key
     * @param keyPrefix
     */
    @Override
    public void delKeysByPrefix(String keyPrefix) {
        Set<String> keys = keys(keyPrefix);
        for (String key : keys) {
            del(key);
        }
    }

}
