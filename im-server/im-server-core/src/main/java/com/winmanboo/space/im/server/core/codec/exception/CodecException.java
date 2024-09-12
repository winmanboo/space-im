package com.winmanboo.space.im.server.core.codec.exception;

import lombok.NoArgsConstructor;

/**
 * 编解码异常
 *
 * @author winmanboo
 * @date 2024/7/28 19:45
 */
@NoArgsConstructor
public class CodecException extends RuntimeException {

    public CodecException(String message) {
        super(message);
    }

    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }

    public CodecException(Throwable cause) {
        super(cause);
    }
}
