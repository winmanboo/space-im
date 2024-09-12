package com.winmanboo.space.im.starter.redis.key;

/**
 * @author winmanboo
 * @date 2024/7/29 19:49
 */
public abstract class SimpleRedisKey {

    private static final String SEPARATOR = ":";

    private static final String PREFIX = "im-server-core";

    private String baseKey() {
        return PREFIX + SEPARATOR + defineRoutingKey();
    }

    protected KeyBuilder keyBuilder() {
        return new KeyBuilder().appendKey(baseKey());
    }

    protected abstract String defineRoutingKey();
}
