package com.winmanboo.space.im.server.core.data;

import lombok.Builder;
import lombok.Data;

/**
 * @author winmanboo
 * @date 2024/7/29 22:16
 */
@Data
@Builder
public class UserClient {

    private String userId;

    private Integer appId;

    private Integer clientType;
}
