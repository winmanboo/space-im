package com.winmanboo.space.im.test.websocket;

import jakarta.websocket.DeploymentException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * @author winmanboo
 * @date 2024/8/16 15:46
 */
@SpringBootApplication
public class WebSocketClient {

    public static void main(String[] args) throws DeploymentException, IOException {
        SpringApplication.run(WebSocketClient.class, args);
    }
}
