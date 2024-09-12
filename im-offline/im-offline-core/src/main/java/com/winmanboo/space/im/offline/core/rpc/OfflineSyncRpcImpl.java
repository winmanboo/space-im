package com.winmanboo.space.im.offline.core.rpc;

import com.winmanboo.space.im.offline.api.dto.OfflineMessageDTO;
import com.winmanboo.space.im.offline.api.rpc.OfflineSyncRpc;
import com.winmanboo.space.im.offline.core.service.OfflineSyncService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;


/**
 * @author winmanboo
 * @date 2024/8/15 15:57
 */
@DubboService
public class OfflineSyncRpcImpl implements OfflineSyncRpc {

    @Resource
    private OfflineSyncService offlineSyncService;

    @Override
    public List<OfflineMessageDTO> sync(String userId, Integer appId, String msgId) {
        return offlineSyncService.sync(userId, appId, msgId);
    }
}
