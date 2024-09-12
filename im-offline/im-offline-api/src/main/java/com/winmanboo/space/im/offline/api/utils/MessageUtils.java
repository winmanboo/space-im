package com.winmanboo.space.im.offline.api.utils;

import com.winmanboo.space.im.offline.api.dto.BodyDTO;
import com.winmanboo.space.im.offline.api.dto.HeaderDTO;
import com.winmanboo.space.im.offline.api.dto.MessageDTO;
import com.winmanboo.space.im.server.api.protocol.message.Body;
import com.winmanboo.space.im.server.api.protocol.message.Header;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import lombok.experimental.UtilityClass;

/**
 * @author winmanboo
 * @date 2024/8/29 15:47
 */
@UtilityClass
public class MessageUtils {

    public static MessageDTO convertToMessageDTO(Message message) {
        Header header = message.getHeader();
        Body body = message.getBody();

        HeaderDTO headerDTO = HeaderDTO.builder()
                .magic(header.getMagic())
                .cmdId(header.getCmdId())
                .bodyLength(header.getBodyLength())
                .version(header.getVersion())
                .objectType(header.getObjectType())
                .clientType(header.getClientType())
                .build();
        BodyDTO bodyDto = BodyDTO.builder()
                .userId(body.getUserId())
                .appId(body.getAppId())
                .toId(body.getToId())
                .msgId(body.getMsgId())
                .cmdId(body.getCmdId())
                .msgType(body.getMsgType())
                .data(body.getData())
                .build();
        return MessageDTO.builder().header(headerDTO).body(bodyDto).build();
    }

    public static Message convertToMessage(MessageDTO messageDTO) {
        HeaderDTO headerDTO = messageDTO.getHeader();
        BodyDTO bodyDTO = messageDTO.getBody();

        Header header = Header.newBuilder()
                .setMagic(headerDTO.getMagic())
                .setCmdId(headerDTO.getCmdId())
                .setBodyLength(headerDTO.getBodyLength())
                .setVersion(headerDTO.getVersion())
                .setObjectType(headerDTO.getObjectType())
                .setClientType(headerDTO.getClientType())
                .build();

        Body body = Body.newBuilder()
                .setUserId(bodyDTO.getUserId())
                .setAppId(bodyDTO.getAppId())
                .setToId(bodyDTO.getToId())
                .setMsgId(bodyDTO.getMsgId())
                .setCmdId(bodyDTO.getCmdId())
                .setMsgType(bodyDTO.getMsgType())
                .setData(bodyDTO.getData())
                .build();

        return Message.newBuilder()
                .setHeader(header)
                .setBody(body)
                .build();
    }
}
