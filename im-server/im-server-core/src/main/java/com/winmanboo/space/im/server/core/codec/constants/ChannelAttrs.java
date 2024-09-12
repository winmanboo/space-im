package com.winmanboo.space.im.server.core.codec.constants;

import io.netty.util.AttributeKey;
import lombok.experimental.UtilityClass;

/**
 * @author winmanboo
 * @date 2024/8/5 13:11
 */
@UtilityClass
public class ChannelAttrs {

    public static final AttributeKey<String> USER_ID_KEY = AttributeKey.valueOf("userId");

    public static final AttributeKey<Integer> APP_ID_KEY = AttributeKey.valueOf("appId");

    public static final AttributeKey<Integer> CLIENT_TYPE = AttributeKey.valueOf("clientType");
}
