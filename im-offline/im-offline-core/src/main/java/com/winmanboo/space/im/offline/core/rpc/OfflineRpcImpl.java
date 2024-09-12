package com.winmanboo.space.im.offline.core.rpc;

import com.winmanboo.space.im.offline.api.dto.MessageDTO;
import com.winmanboo.space.im.offline.api.rpc.OfflineRpc;
import com.winmanboo.space.im.offline.core.service.OfflineMessageService;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;


/**
 * @author winmanboo
 * @date 2024/8/15 14:30
 */
@DubboService
public class OfflineRpcImpl implements OfflineRpc {

    @Resource
    private OfflineMessageService offlineMessageService;

    @Override
    public void offlineMessage(MessageDTO msg) {
        offlineMessageService.offlineMessage(msg);
    }
}
