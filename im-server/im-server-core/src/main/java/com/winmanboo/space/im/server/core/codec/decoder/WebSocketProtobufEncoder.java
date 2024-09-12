package com.winmanboo.space.im.server.core.codec.decoder;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * 将 ByteBuf 转为 WebSocketFrame
 * @author winmanboo
 * @date 2024/7/28 22:02
 */
@Slf4j
public class WebSocketProtobufEncoder extends ProtobufEncoder {

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out) throws Exception {
        ByteBuf buf = null;
        if (msg instanceof MessageLite messageLite) {
            buf = wrappedBuffer(messageLite.toByteArray());
        }
        if (msg instanceof MessageLite.Builder builder) {
            buf = wrappedBuffer((builder.build().toByteArray()));
        }

        assert buf != null;

        BinaryWebSocketFrame frame = new BinaryWebSocketFrame(buf);
        out.add(frame);
    }
}
