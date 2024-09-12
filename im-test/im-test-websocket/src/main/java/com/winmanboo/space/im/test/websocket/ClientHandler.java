package com.winmanboo.space.im.test.websocket;

import jakarta.websocket.*;

/**
 * @author winmanboo
 * @date 2024/8/16 16:15
 */
@ClientEndpoint
public class ClientHandler {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("客户端连接已建立");
    }

    @OnMessage
    public void handleMessage(String message) {
        System.out.println("接收消息：" + message);
    }

    @OnError
    public void onError(Throwable throwable) {
        System.out.println("错误" + throwable.getMessage());
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("连接已关闭: " + closeReason.getReasonPhrase());
    }
}
