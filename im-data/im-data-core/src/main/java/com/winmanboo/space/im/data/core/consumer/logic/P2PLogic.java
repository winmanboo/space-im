package com.winmanboo.space.im.data.core.consumer.logic;

import com.winmanboo.space.im.data.core.consumer.entity.DataMessage;
import com.winmanboo.space.im.offline.api.rpc.OfflineRpc;
import com.winmanboo.space.im.offline.api.dto.MessageDTO;
import com.winmanboo.space.im.offline.api.utils.MessageUtils;
import com.winmanboo.space.im.router.api.rpc.RouterRpc;
import com.winmanboo.space.im.server.api.protocol.message.Body;
import com.winmanboo.space.im.server.api.protocol.message.Header;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import com.winmanboo.space.im.starter.redis.key.RouterRedisKey;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author winmanboo
 * @date 2024/8/13 14:19
 */
@Slf4j
@Component
public class P2PLogic implements Logic {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RouterRedisKey routerRedisKey;

    @DubboReference
    private OfflineRpc offlineRpc;

    @DubboReference
    private RouterRpc routerRpc;

    @Override
    public void handleMessage(Message msg) {
        // TODO 也可以在这里做一些消息的必要性处理，例如敏感词等等

        Body body = msg.getBody();
        Header header = msg.getHeader();

        // 如果该消息已经落库那么不进行处理，因为该消息可能已经进行推送
        if (isMessageExists(body.getMsgId())) {
            log.warn("[P2PLogic] message already exists, msgId: {}", body.getMsgId());
            return;
        }

        // 1. 消息落库
        // 消息落库可能会失败，一定要进行异步重试，否则消息完整性无法保证
        // TODO 建议测试一下什么情况落库会失败
        try {
            insertMessage(msg);
        } catch (Exception e) {
            // TODO 如果是消息落库失败必须要进行异步重试，发送一个 mq 消息，否则消息的完整性就没法保证
        }

        MessageDTO messageDTO = MessageUtils.convertToMessageDTO(msg);

        // 2. 判断用户是否在线，不在线 rpc 到 offline 服务
        // 要单独处理一种情况，就是这里判断用户在线，但是进行路由转发的时候不在线了
        // 所以在路由转发的时候要再次进行判断
        if (!isUserAlive(body.getUserId(), body.getAppId())) {
            offlineRpc.offlineMessage(messageDTO);
        } else {
            // 3. rpc 请求到 router 服务进行路由转发
            // 路由转发消息给目标用户后，没有在规定时间内收到 ack，可以认为此时用户要么是因为网络原因导致
            // 收消息延迟，要么就是用户不在线了，具体怎么判断这里是个难点需要着重考虑一下
            routerRpc.forward(messageDTO);
        }
    }

    private boolean isMessageExists(String msgId) {
        return mongoTemplate.findById(msgId, DataMessage.class) != null;
    }

    private void insertMessage(Message msg) {
        Header header = msg.getHeader();
        Body body = msg.getBody();

        DataMessage dataMessage = DataMessage.builder()
                .cmdId(header.getCmdId())
                .bodyLength(header.getBodyLength())
                .version(header.getVersion())
                .objectType(header.getObjectType())
                .clientType(header.getClientType())
                .userId(body.getUserId())
                .appId(body.getAppId())
                .toId(body.getToId())
                .msgId(body.getMsgId())
                .logicId(body.getCmdId())
                .msgType(body.getMsgType())
                .data(body.getData().toStringUtf8())
                .build();
        mongoTemplate.insert(dataMessage);
    }

    private boolean isUserAlive(String userId, Integer appId) {
        String routerKey = routerRedisKey.getKey(userId, appId);
        return stringRedisTemplate.opsForValue().get(routerKey) != null;
    }
}
