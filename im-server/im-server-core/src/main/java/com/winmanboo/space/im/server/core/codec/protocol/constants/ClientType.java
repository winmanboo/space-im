package com.winmanboo.space.im.server.core.codec.protocol.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author winmanboo
 * @date 2024/7/29 22:16
 */
@Getter
@RequiredArgsConstructor
public enum ClientType {

    BROWSER(1),

    ANDROID(2),

    IOS(3),

    WECHAT(4);

    private final int type;
}
