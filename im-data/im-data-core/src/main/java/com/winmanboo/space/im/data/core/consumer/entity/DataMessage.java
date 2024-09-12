package com.winmanboo.space.im.data.core.consumer.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author winmanboo
 * @date 2024/8/14 18:02
 */
@Data
@Builder
@Document("data_message")
public class DataMessage {

    /**
     * 消息命令
     */
    private Integer cmdId;

    /**
     * 消息提长度
     */
    private Integer bodyLength;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 数据解析类型
     */
    private Integer objectType;

    /**
     * 客户端类型
     */
    private Integer clientType;

    /**
     * 发送方用户 id
     */
    private String userId;

    /**
     * 业务线 id
     */
    private Integer appId;

    /**
     * 接收方 id（如果是 p2p 那么就是用户 id，如果是 p2g 那么就是 groupId）
     */
    private String toId;

    /**
     * 消息 id
     */
    @Id
    private String msgId;

    /**
     * 具体业务逻辑 id
     */
    private Integer logicId;

    /**
     * 消息类型
     */
    private Integer msgType;

    /**
     * 消息包
     */
    private String data;
}
