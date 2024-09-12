package com.winmanboo.space.im.data.core;

import com.winmanboo.space.im.common.api.constants.TopicNames;
import com.winmanboo.space.im.data.core.consumer.ConsumeMessageListener;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author winmanboo
 * @date 2024/8/13 13:22
 */
@Slf4j
@EnableDubbo
@SpringBootApplication
public class ImDataCoreApplication implements InitializingBean {

    @Resource
    private DefaultMQPushConsumer defaultMQPushConsumer;

    @Resource
    private ConsumeMessageListener consumeMessageListener;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ImDataCoreApplication.class);
        application.setWebApplicationType(WebApplicationType.NONE);
        application.run(args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        defaultMQPushConsumer.subscribe(TopicNames.MSG_TRANSFER_TOPIC, "");
        defaultMQPushConsumer.setMessageListener(consumeMessageListener);
        defaultMQPushConsumer.start();
        log.info("[ImDataCoreApplication] run mq consumer success!");
    }
}
