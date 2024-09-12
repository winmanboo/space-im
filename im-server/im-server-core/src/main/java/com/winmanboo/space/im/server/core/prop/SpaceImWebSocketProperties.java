package com.winmanboo.space.im.server.core.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author winmanboo
 * @date 2024/7/28 16:10
 */
@Data
@ConfigurationProperties(prefix = "space.im.websocket")
public class SpaceImWebSocketProperties {

    private Integer port;
}
