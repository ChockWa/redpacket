package com.hdh.redpacket.core.cache;

import java.util.Set;

public interface RedisService {

    void set(String key, Object value);

    void set(String key, Object value, int timeoutSecond);

    void expire(String key, int timeoutSecond);

    Object get(String key);

    void del(String key);

    boolean exists(String key);

    long inc(String key);

    long incBy(String key, long step);

    Set<String> keys(String pattern);

    void delKeysByPrefix(String keyPrefix);
}
