package com.winmanboo.space.im.test.websocket;

import com.google.protobuf.ByteString;
import com.winmanboo.space.im.server.api.protocol.message.Body;
import com.winmanboo.space.im.server.api.protocol.message.Header;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import jakarta.annotation.Resource;
import jakarta.websocket.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * @author winmanboo
 * @date 2024/8/29 10:32
 */
@RestController
public class TestController {

    @Resource
    private Session session;

    @GetMapping("/login")
    public String login(String userId) throws IOException {
        Body body = Body.newBuilder()
                .setUserId(userId)
                .setAppId(1)
                .setMsgId(UUID.randomUUID().toString().substring(0, 5))
                .build();
        Header header = Header.newBuilder()
                .setMagic(0x722819)
                .setCmdId(0x01)
                .setBodyLength(body.toByteArray().length)
                .setVersion(1)
                .build();
        Message message = Message.newBuilder().setHeader(header).setBody(body).build();
        sendData(message);
        return "ok";
    }

    @GetMapping("/send")
    public String send(String userId, String toId, String msg) throws IOException {
        Body body = Body.newBuilder()
                .setUserId(userId)
                .setToId(toId)
                .setAppId(1)
                .setMsgId(UUID.randomUUID().toString().substring(0, 5))
                .setCmdId(0x00)
                .setData(ByteString.copyFrom(msg.getBytes()))
                .build();
        Header header = Header.newBuilder()
                .setMagic(0x722819)
                .setCmdId(0x04)
                .setBodyLength(body.toByteArray().length)
                .setVersion(1)
                .build();
        Message message = Message.newBuilder().setHeader(header).setBody(body).build();
        sendData(message);
        return "ok";
    }

    void sendData(Message message) throws IOException {
        session.getBasicRemote().sendBinary(ByteBuffer.wrap(message.toByteArray()));
    }
}
