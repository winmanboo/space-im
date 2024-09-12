package com.winmanboo.space.im.router.core.exception;

import lombok.NoArgsConstructor;

/**
 * @author winmanboo
 * @date 2024/8/15 16:32
 */
@NoArgsConstructor
public class RouterException extends RuntimeException {

    public RouterException(String message) {
        super(message);
    }

    public RouterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouterException(Throwable cause) {
        super(cause);
    }

    public RouterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
