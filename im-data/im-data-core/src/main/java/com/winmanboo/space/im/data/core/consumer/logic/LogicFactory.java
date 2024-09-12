package com.winmanboo.space.im.data.core.consumer.logic;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author winmanboo
 * @date 2024/8/13 14:18
 */
@Component
public class LogicFactory implements ApplicationContextAware {

    private static final Map<Integer, Logic> LOGIC_MAP = new HashMap<>();

    public Logic getLogic(Integer logicId) {
        return Optional.of(LOGIC_MAP.get(logicId)).orElse(null);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        assert LOGIC_MAP.isEmpty();

        P2PLogic p2PLogic = applicationContext.getBean(P2PLogic.class);
        P2GLogic p2GLogic = applicationContext.getBean(P2GLogic.class);
        FriendApplyLogic friendApplyLogic = applicationContext.getBean(FriendApplyLogic.class);

        LOGIC_MAP.put(LoigcId.P2P.getId(), p2PLogic);
        LOGIC_MAP.put(LoigcId.P2G.getId(), p2GLogic);
        LOGIC_MAP.put(LoigcId.FRIEND_APPLY.getId(), friendApplyLogic);
    }
}
