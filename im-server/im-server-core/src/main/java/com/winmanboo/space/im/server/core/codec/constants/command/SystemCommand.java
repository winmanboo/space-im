package com.winmanboo.space.im.server.core.codec.constants.command;

import lombok.RequiredArgsConstructor;

/**
 * @author winmanboo
 * @date 2024/7/29 11:09
 */
@RequiredArgsConstructor
public enum SystemCommand implements Command {

    /**
     * 登录指令
     */
    LOGIN(0x01),

    /**
     * 登出指令
     */
    LOGOUT(0x02),

    /**
     * 心跳指令
     */
    HEART_BEAT(0x03),

    /**
     * 业务指令
     */
    BIZ(0x04);

    private final int cmd;

    @Override
    public Integer command() {
        return cmd;
    }
}
