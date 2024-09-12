package com.winmanboo.space.im.common.api.constants;

import lombok.experimental.UtilityClass;

/**
 * @author winmanboo
 * @date 2024/8/12 17:35
 */
@UtilityClass
public class TopicNames {

    /**
     * 业务消息流转 topic
     */
    public static final String MSG_TRANSFER_TOPIC = "space-im-msg-transfer-topic";

    /**
     * 消息推送 topic，后面跟节点 ip 地址
     */
    public static final String MSG_PUSH_TOPIC = "space-im-msg-push-topic-";
}
