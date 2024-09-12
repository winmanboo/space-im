package com.winmanboo.space.im.starter.mq.config;

import lombok.Data;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author winmanboo
 * @date 2024/8/13 14:58
 */
@Data
@ConfigurationProperties(prefix = "space.mq")
public class RocketMQProperties {

    private String nameServer = "127.0.0.1";

    private Producer producer;

    private Consumer consumer;

    @Data
    public static class Producer {

        /**
         * enable of producer
         */
        private Boolean enable = false;

        /**
         * name of producer
         */
        private String group;

        /**
         * millis of send message timeout
         */
        private int sendMsgTimeout = 3000;

        /**
         * Compress message body threshold, namely, message body larger than 4k will be compressed on default.
         */
        private int compressMsgBodyOverHowmuch = 1024 * 4;

        /**
         * <p> Maximum number of retry to perform internally before claiming sending failure in synchronous mode. </p>
         * This may potentially cause message duplication which is up to application developers to resolve.
         */
        private int retryTimesWhenSendFailed = 2;

        /**
         * <p> Maximum number of retry to perform internally before claiming sending failure in asynchronous mode. </p>
         * This may potentially cause message duplication which is up to application developers to resolve.
         */
        private int retryTimesWhenSendAsyncFailed = 2;

        /**
         * Indicate whether to retry another broker on sending failure internally.
         */
        private boolean retryAnotherBrokerWhenNotStoreOk = false;

        /**
         * Maximum allowed message size in bytes. // 4M
         */
        private int maxMessageSize = 1024 * 1024 * 4;
    }

    @Data
    public static class Consumer {

        /**
         * enable of consumer
         */
        private Boolean enable = false;

        /**
         * name of consumer
         */
        private String group;

        private boolean vipChannelEnabled = false;

        /**
         * 批量消费的数量
         */
        private Integer consumeMessageBatchMaxSize = 1;

        /**
         * 从哪个位置开始消费
         */
        private ConsumeFromWhere consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;
    }
}
