package com.winmanboo.space.im.server.core.codec.constants.command;

import lombok.RequiredArgsConstructor;

/**
 * @author winmanboo
 * @date 2024/7/29 11:15
 */
@RequiredArgsConstructor
public enum BizCommand implements Command {

    ;

    private final int cmd;

    @Override
    public Integer command() {
        return cmd;
    }
}
