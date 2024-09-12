package com.winmanboo.space.im.server.core.consume;

import com.google.protobuf.InvalidProtocolBufferException;
import com.winmanboo.space.im.server.api.protocol.message.Body;
import com.winmanboo.space.im.server.api.protocol.message.Header;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.ConfirmAck;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.ConfirmAck.Ack;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.ConfirmAck.ResultCode;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.MessagePack;
import com.winmanboo.space.im.server.core.data.UserClient;
import com.winmanboo.space.im.server.core.register.ServerRegister;
import io.netty.channel.Channel;
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
 * @date 2024/8/16 10:58
 */
@Slf4j
@Component
public class ConsumePushMessageListener implements MessageListenerConcurrently {

    @Resource
    private ServerRegister serverRegister;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        msgs.forEach(messageExt -> {
            byte[] body = messageExt.getBody();
            try {
                Message message = Message.parseFrom(body);
                Body messageBody = message.getBody();
                Header header = message.getHeader();
                UserClient userClient = UserClient.builder()
                        .userId(messageBody.getToId())
                        .appId(messageBody.getAppId())
                        .clientType(header.getClientType())
                        .build();
                Channel userChannel = serverRegister.findUserChannel(userClient);
                if (userChannel == null) {
                    log.error("[ConsumePushMessageListener] channel 获取失败，推送消息失败");
                    return;
                }
                //  接收方收到后要回复一个确认 ack
                MessagePack messagePack = MessagePack.newBuilder()
                        .setUserId(messageBody.getUserId())
                        .setToId(messageBody.getToId())
                        .setMsgId(messageBody.getMsgId())
                        .setMsgType(messageBody.getMsgType())
                        .setData(messageBody.getData().toStringUtf8())
                        .build();
                ConfirmAck ack = ConfirmAck.newBuilder()
                        .setAck(Ack.MESSAGE_VALUE)
                        .setMessageId(messageBody.getMsgId())
                        .setResultCode(ResultCode.OK_VALUE)
                        .setData(messagePack)
                        .build();
                userChannel.writeAndFlush(ack);
            } catch (InvalidProtocolBufferException e) {
                log.error("[ConsumePushMessageListener] message parse error.");
            }
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
