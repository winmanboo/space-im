package com.winmanboo.space.im.starter.mq.config;

import com.winmanboo.space.im.starter.mq.config.RocketMQProperties.Consumer;
import com.winmanboo.space.im.starter.mq.config.RocketMQProperties.Producer;
import jakarta.annotation.Resource;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author winmanboo
 * @date 2024/8/13 14:54
 */
@AutoConfiguration
@EnableConfigurationProperties(RocketMQProperties.class)
public class RocketMQProducerConfig {

    private static final ThreadPoolExecutor defaultAsyncSenderExecutor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            1000 * 60L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(50000),
            new ThreadFactory() {
                private final AtomicInteger threadIndex = new AtomicInteger(0);

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "rocketmq-async-thread-" + this.threadIndex.incrementAndGet());
                }
            });

    @Resource
    private RocketMQProperties rocketMQProperties;

    @Bean
    @ConditionalOnProperty(prefix = "space.mq.producer", name = "enable")
    public DefaultMQProducer defaultMQProducer() {
        return getDefaultMQProducer();
    }

    @Bean
    @ConditionalOnProperty(prefix = "space.mq.consumer", name = "enable")
    public DefaultMQPushConsumer defaultMQPushConsumer() {
        return getDefaultMQPushConsumer();
    }

    private DefaultMQPushConsumer getDefaultMQPushConsumer() {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer();
        Consumer consumer = rocketMQProperties.getConsumer();
        defaultMQPushConsumer.setNamesrvAddr(rocketMQProperties.getNameServer());
        defaultMQPushConsumer.setVipChannelEnabled(consumer.isVipChannelEnabled());
        defaultMQPushConsumer.setConsumerGroup(consumer.getGroup());
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(consumer.getConsumeMessageBatchMaxSize());
        defaultMQPushConsumer.setConsumeFromWhere(consumer.getConsumeFromWhere());
        return defaultMQPushConsumer;
    }

    private DefaultMQProducer getDefaultMQProducer() {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setNamesrvAddr(rocketMQProperties.getNameServer());
        Producer producer = rocketMQProperties.getProducer();
        defaultMQProducer.setProducerGroup(producer.getGroup());
        defaultMQProducer.setRetryTimesWhenSendFailed(producer.getRetryTimesWhenSendFailed());
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(producer.getRetryTimesWhenSendAsyncFailed());
        // 设置失败后的重试是否切换另一个 broker
        defaultMQProducer.setRetryAnotherBrokerWhenNotStoreOK(producer.isRetryAnotherBrokerWhenNotStoreOk());
        defaultMQProducer.setAsyncSenderExecutor(defaultAsyncSenderExecutor);
        return defaultMQProducer;
    }
}
