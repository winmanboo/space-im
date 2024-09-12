package com.winmanboo.space.im.server.core.handler.core;

import com.winmanboo.space.im.server.api.protocol.message.Header;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import com.winmanboo.space.im.server.core.codec.protocol.Protocol;
import com.winmanboo.space.im.server.core.handler.strategy.CommandStrategy;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author winmanboo
 * @date 2024/7/28 22:11
 */
@Slf4j
@Sharable
@Component
public class WebSocketNettyServerHandler extends SimpleChannelInboundHandler<Message> {

    @Resource
    private CommandStrategy commandStrategy;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("[WebSocketNettyServerHandler] 客户端连接");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.info("[WebSocketNettyServerHandler] 客户端断连");
        // serverRegister.removeRoutingTable();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        Header header = msg.getHeader();
        boolean checkResult = Protocol.necessaryHeaderCheck(header);
        if (!checkResult) {
            // 返回 ack 告知用户此消息格式错误
            return;
        }

        commandStrategy.findOut(header.getCmdId()).execute(ctx, msg);
    }
}
