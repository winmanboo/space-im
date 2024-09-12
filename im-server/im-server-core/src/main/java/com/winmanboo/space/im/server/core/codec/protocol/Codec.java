package com.winmanboo.space.im.server.core.codec.protocol;

import com.winmanboo.space.im.server.api.protocol.message.Header;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import com.winmanboo.space.im.server.core.codec.exception.CodecException;
import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static com.winmanboo.space.im.server.core.codec.protocol.Protocol.MAGIC_NUMBER;

/**
 * 编解码工具类
 *
 * @author winmanboo
 * @date 2024/7/28 19:37
 */
@Slf4j
@UtilityClass
public class Codec {

    @SneakyThrows
    public static Message decode(ByteBuf in) {
        int magic = in.readInt();

        if (MAGIC_NUMBER != magic) {
            log.error("[Codec] magic number is incorrect.");
            throw new CodecException("magic number is incorrect.");
        }

        int cmdId = in.readInt();
        int bodyLength = in.readInt();
        int version = in.readInt();
        int objectType = in.readInt();
        int clientType = in.readInt();
        byte[] body = new byte[bodyLength];
        in.readBytes(body);

        Header header = Header.newBuilder()
                .setMagic(magic)
                .setCmdId(cmdId)
                .setBodyLength(bodyLength)
                .setVersion(version)
                .setObjectType(objectType)
                .setClientType(clientType)
                .build();

        return null;
    }
}
