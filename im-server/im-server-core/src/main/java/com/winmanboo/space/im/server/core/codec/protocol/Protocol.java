package com.winmanboo.space.im.server.core.codec.protocol;

import com.winmanboo.space.im.server.api.protocol.message.Header;
import io.netty.buffer.ByteBuf;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author winmanboo
 * @date 2024/7/28 19:26
 */
@Slf4j
@UtilityClass
public class Protocol {

    /**
     * 协议魔数
     */
    public static final Integer MAGIC_NUMBER = 0x722819;

    /**
     * 消息包至少所占字节数
     */
    public static final Integer CODEC_MIN_PACKET_LENGTH = 28;

    /**
     * 最小消息包检查
     *
     * @param in ByteBuf inbound
     * @return result
     */
    public static boolean ensureMessagePackLength(ByteBuf in) {
        return in.readableBytes() > CODEC_MIN_PACKET_LENGTH;
    }

    /**
     * 魔数校验
     *
     * @param magicNumber 魔数
     * @return result
     */
    public static boolean ensureMagicNumber(Integer magicNumber) {
        return magicNumber != null && magicNumber.equals(MAGIC_NUMBER);
    }

    public static boolean necessaryHeaderCheck(Header header) {
        // add more check step...
        return ensureMagicNumber(header.getMagic());
    }
}
