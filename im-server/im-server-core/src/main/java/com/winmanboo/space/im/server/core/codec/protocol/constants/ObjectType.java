package com.winmanboo.space.im.server.core.codec.protocol.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author winmanboo
 * @date 2024/7/28 21:53
 */
@Getter
@RequiredArgsConstructor
public enum ObjectType {

    PROTO(0x01, "protobuf"),

    JSON(0x02, "json");

    private final int code;

    private final String desc;
}
