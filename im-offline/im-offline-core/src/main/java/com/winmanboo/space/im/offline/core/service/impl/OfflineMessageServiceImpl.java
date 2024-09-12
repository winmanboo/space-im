package com.winmanboo.space.im.offline.core.service.impl;

import com.winmanboo.space.im.offline.api.dto.BodyDTO;
import com.winmanboo.space.im.offline.api.dto.MessageDTO;
import com.winmanboo.space.im.offline.core.entity.OfflineMessage;
import com.winmanboo.space.im.offline.core.service.OfflineMessageService;
import com.winmanboo.space.im.server.api.protocol.message.Body;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import jakarta.annotation.Resource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


/**
 * @author winmanboo
 * @date 2024/8/15 14:32
 */
@Service
public class OfflineMessageServiceImpl implements OfflineMessageService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void offlineMessage(MessageDTO msg) {
        // 1. 离线消息的落库
        insertOfflineMessage(msg);

        // 2. 三方推送
        pushClient(msg);
    }

    private void insertOfflineMessage(MessageDTO msg) {
        BodyDTO body = msg.getBody();

        OfflineMessage offlineMessage = OfflineMessage.builder()
                .userId(body.getUserId())
                .appId(body.getAppId())
                .toId(body.getToId())
                .msgId(body.getMsgId())
                .logicId(body.getCmdId())
                .msgType(body.getMsgType())
                .data(body.getData().toStringUtf8())
                .synced(false)
                .build();
        mongoTemplate.insert(offlineMessage);
    }

    private void pushClient(MessageDTO msg) {
        // TODO 三方推送
    }
}
