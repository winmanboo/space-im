package com.winmanboo.space.im.server.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author winmanboo
 * @date 2024/7/28 16:00
 */
@Slf4j
@EnableDubbo
@SpringBootApplication
public class ImServerCoreApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ImServerCoreApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }
}
