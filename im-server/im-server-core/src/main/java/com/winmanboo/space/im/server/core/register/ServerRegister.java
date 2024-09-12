package com.winmanboo.space.im.server.core.register;

import com.winmanboo.space.im.server.api.protocol.message.Body;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import com.winmanboo.space.im.server.core.codec.constants.ChannelAttrs;
import com.winmanboo.space.im.server.core.data.UserClient;
import com.winmanboo.space.im.starter.redis.key.RouterRedisKey;
import io.netty.channel.Channel;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author winmanboo
 * @date 2024/7/29 13:26
 */
@Component
public class ServerRegister {

    private String SERVER_ADDRESS = null;

    private static final Map<UserClient, Channel> USER_CLIENT_CHANNEL_MAP = new ConcurrentHashMap<>();

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RouterRedisKey routerRedisKey;

    private final Object bindLock = new Object();
    private final Object unbindLock = new Object();

    public void register() {
        SERVER_ADDRESS = ipAddress();
    }

    public String getCurrentServerAddress() {
        assert SERVER_ADDRESS != null;
        return SERVER_ADDRESS;
    }

    /**
     * 绑定服务
     *
     * @param msg        消息
     * @param channel    channel
     * @param serverType 服务器类型
     */
    public void bindServer(Message msg, Channel channel) {
        synchronized (bindLock) {
            Body body = msg.getBody();
            String userId = body.getUserId();
            int appId = body.getAppId();
            // 路由表
            registerRoutingTable(userId, appId);
            // 用户 channel
            registerUserChannel(msg, channel);
        }
    }

    /**
     * 根据 channel 反查 UserClient
     *
     * @param channel channel
     * @return userClient
     * @see UserClient
     */
    public UserClient findUserClient(Channel channel) {
        String userId = channel.attr(ChannelAttrs.USER_ID_KEY).get();
        Integer appId = channel.attr(ChannelAttrs.APP_ID_KEY).get();
        Integer clientType = channel.attr(ChannelAttrs.CLIENT_TYPE).get();

        return UserClient.builder()
                .userId(userId)
                .appId(appId)
                .clientType(clientType)
                .build();
    }

    /**
     * 根据客户端信息找到绑定的 Channel
     *
     * @param userClient 客户端信息
     * @return Channel
     */
    public Channel findUserChannel(UserClient userClient) {
        return USER_CLIENT_CHANNEL_MAP.get(userClient);
    }

    /**
     * 注册用户与 Channel 的映射关系
     *
     * @param msg     消息
     * @param channel channel
     */
    private void registerUserChannel(Message msg, Channel channel) {
        Body body = msg.getBody();
        UserClient userClient = UserClient.builder()
                .userId(body.getUserId())
                .appId(body.getAppId())
                .clientType(msg.getHeader().getClientType())
                .build();

        channel.attr(ChannelAttrs.USER_ID_KEY).set(userClient.getUserId());
        channel.attr(ChannelAttrs.APP_ID_KEY).set(userClient.getAppId());
        channel.attr(ChannelAttrs.CLIENT_TYPE).set(userClient.getClientType());

        USER_CLIENT_CHANNEL_MAP.put(userClient, channel);
    }

    /**
     * 注册路由表
     *
     * @param userId     用户 id
     * @param appId      产品 id
     * @param serverType 服务器类型
     */
    private void registerRoutingTable(String userId, Integer appId) {
        String addr = getCurrentServerAddress();
        String key = routerRedisKey.getKey(userId, appId);
        stringRedisTemplate.opsForValue().set(key, addr);
    }

    public void removeRoutingTable(String userId, Integer appId) {
        String key = routerRedisKey.getKey(userId, appId);
        stringRedisTemplate.delete(key);
    }

    @SneakyThrows
    private String ipAddress() {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
