package com.winmanboo.space.im.router.core.service.impl;

import com.winmanboo.space.im.common.api.constants.TopicNames;
import com.winmanboo.space.im.offline.api.dto.BodyDTO;
import com.winmanboo.space.im.offline.api.dto.MessageDTO;
import com.winmanboo.space.im.offline.api.rpc.OfflineRpc;
import com.winmanboo.space.im.offline.api.utils.MessageUtils;
import com.winmanboo.space.im.router.core.service.RouterService;
import com.winmanboo.space.im.starter.redis.key.RouterRedisKey;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author winmanboo
 * @date 2024/8/15 16:23
 */
@Slf4j
@Service
public class RouterServiceImpl implements RouterService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RouterRedisKey routerRedisKey;

    @DubboReference
    private OfflineRpc offlineRpc;

    @Resource
    private DefaultMQProducer defaultMQProducer;

    @Override
    public void forward(MessageDTO msg) {

        BodyDTO body = msg.getBody();
        String nodeIp = fetchNodeIp(body.getUserId(), body.getAppId());
        // 节点 ip 为空，可能是用户下线了
        if (nodeIp == null) {
            log.warn("[forward] node ip is null");
            // 走离线消息
            offlineRpc.offlineMessage(msg);
            return;
        }

        // 通过 mq 推送消息
        pushMessage(msg, nodeIp);
    }

    @SneakyThrows
    private void pushMessage(MessageDTO msg, String nodeIp) {
        org.apache.rocketmq.common.message.Message rocketMsg = new org.apache.rocketmq.common.message.Message();
        String topic = TopicNames.MSG_PUSH_TOPIC + nodeIp.replace(".", "_");
        rocketMsg.setTopic(topic);
        rocketMsg.setBody(MessageUtils.convertToMessage(msg).toByteArray());
        defaultMQProducer.send(rocketMsg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("[pushMessage] send message success, msgId: {}", msg.getBody().getMsgId());
            }

            @Override
            public void onException(Throwable e) {
                log.error("[pushMessage] send error", e);
                // TODO 可以进行重试操作
            }
        }, 1500);
    }

    private String fetchNodeIp(String userId, Integer appId) {
        String key = routerRedisKey.getKey(userId, appId);
        return stringRedisTemplate.opsForValue().get(key);
    }
}
