package com.winmanboo.space.im.server.core.handler.executor;

import com.winmanboo.space.im.common.api.constants.TopicNames;
import com.winmanboo.space.im.server.api.protocol.message.Body;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.ConfirmAck;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.ConfirmAck.Ack;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.ConfirmAck.ResultCode;
import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.stereotype.Component;

/**
 * @author winmanboo
 * @date 2024/7/28 22:43
 */
@Slf4j
@Component
public class BizCommandExecutor implements CommandExecutor {

    private static final long MSG_TIMEOUT = 3000L;

    @Resource
    private DefaultMQProducer defaultMQProducer;

    @Override
    public void execute(ChannelHandlerContext ctx, Message msg) {
        Body body = msg.getBody();
        int logicId = body.getCmdId();

        // TODO 校验消息是否有权限发送

        asyncSend(ctx, msg);
    }

    // 异步发送
    @SneakyThrows
    private void asyncSend(ChannelHandlerContext ctx, Message msg) {
        org.apache.rocketmq.common.message.Message rocketMsg = new org.apache.rocketmq.common.message.Message();
        rocketMsg.setTopic(TopicNames.MSG_TRANSFER_TOPIC);
        rocketMsg.setBody(msg.toByteArray());
        String msgId = msg.getBody().getMsgId();
        if (msgId.isEmpty()) {
            log.error("[asyncSend] msgId 不能为空！");
            sendAck(ctx, msgId, ResultCode.FAIL);
            return;
        }
        defaultMQProducer.send(rocketMsg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                // 回复一个确认 ack
                log.info("[asyncSend] 消息发送成功，msgId: {}", msgId);
                sendAck(ctx, msgId, ResultCode.OK);
            }

            @Override
            public void onException(Throwable e) {
                // 此时发送端如果异步重试后还是失败，丢掉消息回复一个失败 ack
                log.error("[asyncSend] 消息发送失败，msgId: {}, error: {}", msgId, e.getMessage());
                // 回复一个失败 ack 告知用户消息发送失败
                sendAck(ctx, msgId, ResultCode.FAIL);
            }
        }, MSG_TIMEOUT);
    }

    // 发送 ack
    private void sendAck(ChannelHandlerContext ctx, String msgId, ResultCode resultCode) {
        ConfirmAck ack = ConfirmAck.newBuilder()
                .setAck(Ack.ACK_VALUE)
                .setMessageId(msgId)
                .setResultCode(resultCode.getNumber())
                .build();
        ctx.writeAndFlush(ack);
    }
}
