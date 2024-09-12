package com.winmanboo.space.im.starter.redis.key;

import org.springframework.stereotype.Component;

/**
 * @author winmanboo
 * @date 2024/7/29 19:33
 */
@Component
public class RouterRedisKey extends SimpleRedisKey {

    @Override
    protected String defineRoutingKey() {
        return "routing_table";
    }

    public String getKey(String userId, Integer appId) {
        return keyBuilder()
                .appendKey(appId)
                .appendKey(userId)
                .build();
    }
}
