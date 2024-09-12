package com.winmanboo.space.im.server.core.handler.executor;

import com.winmanboo.space.im.server.api.protocol.message.Message;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author winmanboo
 * @date 2024/7/28 22:30
 */
public interface CommandExecutor {

    /**
     * 执行命令
     *
     * @param ctx        context
     * @param msg        消息
     * @param serverType 服务类型
     */
    void execute(ChannelHandlerContext ctx, Message msg);
}
