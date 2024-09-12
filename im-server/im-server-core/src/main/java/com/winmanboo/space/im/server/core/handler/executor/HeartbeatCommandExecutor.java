package com.winmanboo.space.im.server.core.handler.executor;

import com.winmanboo.space.im.server.api.protocol.message.Message;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author winmanboo
 * @date 2024/7/28 22:40
 */
@Slf4j
@Component
public class HeartbeatCommandExecutor implements CommandExecutor {
    @Override
    public void execute(ChannelHandlerContext ctx, Message msg) {

    }
}
