package com.winmanboo.space.im.offline.core;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

/**
 * @author winmanboo
 * @date 2024/8/15 11:10
 */
@EnableDubbo
@SpringBootApplication
public class OfflineCoreApplication {

    private static final CountDownLatch COUNTDOWNLATCH = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        SpringApplication application = new SpringApplication(OfflineCoreApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
        COUNTDOWNLATCH.await();
    }
}
