package com.winmanboo.space.im.router.api.rpc;


import com.winmanboo.space.im.offline.api.dto.MessageDTO;

/**
 * @author winmanboo
 * @date 2024/8/15 16:19
 */
public interface RouterRpc {

    /**
     * 路由转发消息
     *
     * @param msg 消息
     */
    void forward(MessageDTO messageDTO);
}
