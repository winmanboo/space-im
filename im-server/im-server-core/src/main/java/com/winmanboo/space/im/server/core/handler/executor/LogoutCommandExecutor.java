package com.winmanboo.space.im.server.core.handler.executor;

import com.winmanboo.space.im.server.api.protocol.message.Message;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author winmanboo
 * @date 2024/7/28 22:38
 */
@Slf4j
@Component
public class LogoutCommandExecutor implements CommandExecutor {

    @Override
    public void execute(ChannelHandlerContext ctx, Message msg) {

    }
}
