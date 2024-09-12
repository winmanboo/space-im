package com.winmanboo.space.im.server.core.handler.strategy;

import com.winmanboo.space.im.server.core.codec.constants.command.SystemCommand;
import com.winmanboo.space.im.server.core.codec.exception.CommandException;
import com.winmanboo.space.im.server.core.handler.executor.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author winmanboo
 * @date 2024/7/29 11:01
 */
@Component
public class CommandStrategy implements ApplicationContextAware {

    private static final Map<Integer, CommandExecutor> EXECUTOR_MAP = new HashMap<>();

    public CommandExecutor findOut(Integer command) {
        if (command == null) {
            throw new CommandException("command can not be null!");
        }
        return EXECUTOR_MAP.get(command);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LoginCommandExecutor loginCommandExecutor = applicationContext.getBean(LoginCommandExecutor.class);
        LogoutCommandExecutor logoutCommandExecutor = applicationContext.getBean(LogoutCommandExecutor.class);
        HeartbeatCommandExecutor heartbeatCommandExecutor = applicationContext.getBean(HeartbeatCommandExecutor.class);
        BizCommandExecutor bizCommandExecutor = applicationContext.getBean(BizCommandExecutor.class);

        EXECUTOR_MAP.put(SystemCommand.LOGIN.command(), loginCommandExecutor);
        EXECUTOR_MAP.put(SystemCommand.LOGOUT.command(), logoutCommandExecutor);
        EXECUTOR_MAP.put(SystemCommand.HEART_BEAT.command(), heartbeatCommandExecutor);
        EXECUTOR_MAP.put(SystemCommand.BIZ.command(), bizCommandExecutor);
    }
}
