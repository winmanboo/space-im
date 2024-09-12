package com.winmanboo.space.im.offline.api.rpc;

import com.winmanboo.space.im.offline.api.dto.MessageDTO;

/**
 * @author winmanboo
 * @date 2024/8/15 14:29
 */
public interface OfflineRpc {

    /**
     * 用户离线消息
     *
     * @param msg 消息
     */
    void offlineMessage(MessageDTO messageDTO);
}
