package com.winmanboo.space.im.test.websocket;

import jakarta.websocket.ContainerProvider;
import jakarta.websocket.DeploymentException;
import jakarta.websocket.Session;
import jakarta.websocket.WebSocketContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;

/**
 * @author winmanboo
 * @date 2024/8/29 10:35
 */
@Configuration
public class WebScoketConfig {

    @Bean
    public Session session() throws DeploymentException, IOException {
        return startWS();
    }

    Session startWS() throws DeploymentException, IOException {
        WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();
        // 设置消息大小最大为 10M
        webSocketContainer.setDefaultMaxBinaryMessageBufferSize(10 * 1024 * 1024);
        webSocketContainer.setDefaultMaxTextMessageBufferSize(10 * 1024 * 1024);
        // 客户端，连接 ws
        String uri = "ws://localhost:8085/space-ws";
        return webSocketContainer.connectToServer(ClientHandler.class, URI.create(uri));
    }
}
