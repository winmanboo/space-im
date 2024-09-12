package com.winmanboo.space.im.offline.api.dto;

import lombok.Data;

/**
 * @author winmanboo
 * @date 2024/8/15 14:27
 */
@Data
public class OfflineMessageDTO {

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

    /**
     * 同步标志
     */
    private Boolean synced;
}
