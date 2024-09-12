package com.winmanboo.space.im.offline.core.service.impl;

import com.mongodb.client.result.UpdateResult;
import com.winmanboo.space.im.offline.api.dto.OfflineMessageDTO;
import com.winmanboo.space.im.offline.core.entity.OfflineMessage;
import com.winmanboo.space.im.offline.core.mapper.OfflineMessageMapper;
import com.winmanboo.space.im.offline.core.service.OfflineSyncService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author winmanboo
 * @date 2024/8/15 15:57
 */
@Slf4j
@Service
public class OfflineSyncServiceImpl implements OfflineSyncService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private OfflineMessageMapper offlineMessageMapper;

    @Override
    public List<OfflineMessageDTO> sync(String userId, Integer appId, String msgId) {
        Criteria criteria = Criteria.where("userId").is(userId)
                .and("appId").is(appId)
                .and("msgId").is(msgId);

        Query query = Query.query(criteria);
        List<OfflineMessage> offlineMessageList = mongoTemplate.find(query, OfflineMessage.class);
        Update update = Update.update("synced", true);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, OfflineMessage.class);
        if (!updateResult.wasAcknowledged()) {
            log.error("同步更新离线消息失败");
            // TODO 这里应该进行消息队列的异步重试
        }
        return offlineMessageMapper.toDtoList(offlineMessageList);
    }
}
