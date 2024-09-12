package com.winmanboo.space.im.router.core.rpc;

import com.winmanboo.space.im.offline.api.dto.MessageDTO;
import com.winmanboo.space.im.router.api.rpc.RouterRpc;
import com.winmanboo.space.im.router.core.service.RouterService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author winmanboo
 * @date 2024/8/15 16:23
 */
@DubboService
public class RouterRpcImpl implements RouterRpc {

    @Resource
    private RouterService routerService;

    @Override
    public void forward(MessageDTO messageDTO) {
        routerService.forward(messageDTO);
    }
}
