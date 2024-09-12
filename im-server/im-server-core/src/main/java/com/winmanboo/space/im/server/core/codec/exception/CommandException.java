package com.winmanboo.space.im.server.core.codec.exception;

import lombok.NoArgsConstructor;

/**
 * 编解码异常
 *
 * @author winmanboo
 * @date 2024/7/28 19:45
 */
@NoArgsConstructor
public class CommandException extends RuntimeException {

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
