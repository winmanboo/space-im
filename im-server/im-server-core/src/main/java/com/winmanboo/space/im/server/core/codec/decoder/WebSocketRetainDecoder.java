package com.winmanboo.space.im.server.core.codec.decoder;

import com.winmanboo.space.im.server.core.codec.protocol.Protocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 将 WebSocketFrame 转为 ByteBuf
 *
 * @author winmanboo
 * @date 2024/7/28 16:44
 */
@Slf4j
public class WebSocketRetainDecoder extends MessageToMessageDecoder<BinaryWebSocketFrame> {

    @Override
    protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame msg, List<Object> out) {
        ByteBuf content = msg.content();

        if (Protocol.ensureMessagePackLength(content)) {
            content.retain();
            out.add(content);
            return;
        }

        log.info("packet format error.");
        ctx.close();
    }
}
