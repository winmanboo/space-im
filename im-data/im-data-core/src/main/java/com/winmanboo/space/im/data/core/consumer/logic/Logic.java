package com.winmanboo.space.im.data.core.consumer.logic;

import com.winmanboo.space.im.server.api.protocol.message.Message;

/**
 * @author winmanboo
 * @date 2024/8/13 14:18
 */
public interface Logic {

    void handleMessage(Message msg);
}
