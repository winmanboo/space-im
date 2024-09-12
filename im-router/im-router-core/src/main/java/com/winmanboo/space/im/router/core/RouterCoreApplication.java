package com.winmanboo.space.im.router.core;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author winmanboo
 * @date 2024/8/15 16:22
 */
@Slf4j
@EnableDubbo
@SpringBootApplication
public class RouterCoreApplication implements InitializingBean {

    @Resource
    private DefaultMQProducer defaultMQProducer;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(RouterCoreApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        defaultMQProducer.start();
        log.info("[RouterCoreApplication] mq producer start success.");
    }
}
