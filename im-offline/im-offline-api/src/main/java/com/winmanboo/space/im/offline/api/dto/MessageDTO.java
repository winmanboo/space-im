package com.winmanboo.space.im.offline.api.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author winmanboo
 * @date 2024/8/29 15:09
 */
@Data
@Builder
public class MessageDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private HeaderDTO header;

    private BodyDTO body;
}
