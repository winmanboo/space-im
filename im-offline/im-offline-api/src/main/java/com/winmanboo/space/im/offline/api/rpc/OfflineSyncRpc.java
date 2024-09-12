package com.winmanboo.space.im.offline.api.rpc;

import com.winmanboo.space.im.offline.api.dto.OfflineMessageDTO;

import java.util.List;

/**
 * @author winmanboo
 * @date 2024/8/15 14:53
 */
public interface OfflineSyncRpc {

    /**
     * 离线数据同步
     *
     * @param userId 用户 id
     * @param appId  业务线 id
     * @param msgId  消息 id
     * @return 未同步的离线消息
     */
    List<OfflineMessageDTO> sync(String userId, Integer appId, String msgId);
}
