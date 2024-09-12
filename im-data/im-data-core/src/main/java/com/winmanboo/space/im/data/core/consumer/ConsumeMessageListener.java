package com.winmanboo.space.im.data.core.consumer;

import com.google.protobuf.InvalidProtocolBufferException;
import com.winmanboo.space.im.data.core.consumer.logic.Logic;
import com.winmanboo.space.im.data.core.consumer.logic.LogicFactory;
import com.winmanboo.space.im.server.api.protocol.message.Body;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author winmanboo
 * @date 2024/8/15 13:51
 */
@Slf4j
@Component
public class ConsumeMessageListener implements MessageListenerConcurrently {

    @Resource
    private LogicFactory logicFactory;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        msgs.forEach(messageExt -> {
            try {
                Message message = Message.parseFrom(messageExt.getBody());
                Body body = message.getBody();
                int logicId = body.getCmdId();
                Logic logic = logicFactory.getLogic(logicId);
                if (logic != null) {
                    // 这里如果消费失败，我们就要确定为什么消费失败，然后直接存离线消息库中，通知客户端手动同步一下消息
                    logic.handleMessage(message);
                } else {
                    log.error("[ConsumeMessageListener] 无法处理该消息，msgId: {}", body.getMsgId());
                    // TODO 通知用户该消息发送失败（最好携带原因，方便排查）
                }
            } catch (InvalidProtocolBufferException e) {
                log.error("[ConsumeMessageListener] message parse error!");
            }
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
