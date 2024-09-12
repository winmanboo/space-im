package com.winmanboo.space.im.offline.api.dto;

import com.google.protobuf.ByteString;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author winmanboo
 * @date 2024/8/29 15:10
 */
@Data
@Builder
public class BodyDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String userId;

    private Integer appId;

    private String toId;

    private String msgId;

    private Integer cmdId;

    private Integer msgType;

    private ByteString data;
}
