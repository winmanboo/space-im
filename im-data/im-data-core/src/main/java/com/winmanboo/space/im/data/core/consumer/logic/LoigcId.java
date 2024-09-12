package com.winmanboo.space.im.data.core.consumer.logic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author winmanboo
 * @date 2024/8/13 14:21
 */
@Getter
@RequiredArgsConstructor
public enum LoigcId {

    P2P(0),

    P2G(1),

    FRIEND_APPLY(3);

    private final int id;
}
