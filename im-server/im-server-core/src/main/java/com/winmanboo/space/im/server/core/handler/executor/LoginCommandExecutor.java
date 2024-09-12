package com.winmanboo.space.im.server.core.handler.executor;

import com.alibaba.fastjson2.JSON;
import com.winmanboo.space.im.offline.api.dto.OfflineMessageDTO;
import com.winmanboo.space.im.offline.api.rpc.OfflineSyncRpc;
import com.winmanboo.space.im.server.api.protocol.message.Body;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.ConfirmAck;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.ConfirmAck.Ack;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.ConfirmAck.ResultCode;
import com.winmanboo.space.im.server.core.codec.protocol.pack.ack.MessagePack;
import com.winmanboo.space.im.server.core.register.ServerRegister;
import io.netty.channel.ChannelHandlerContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author winmanboo
 * @date 2024/7/28 22:38
 */
@Slf4j
@Component
public class LoginCommandExecutor implements CommandExecutor {

    @Resource
    private ServerRegister serverRegister;

    @DubboReference
    private OfflineSyncRpc offlineSyncRpc;

    @Override
    public void execute(ChannelHandlerContext ctx, Message msg) {
        // TODO 验证用户合法性

        // 登录绑定
        serverRegister.bindServer(msg, ctx.channel());
        // TODO 可以广播一条用户上线的消息（或者只对你关注的在线好友进行推送）

        // TODO 用户上线后进行离线数据同步，如果是新设备直接进行数据漫游
        Body body = msg.getBody();
        List<OfflineMessageDTO> offlineMessageDTOList = offlineSyncRpc.sync(body.getUserId(), body.getAppId(), body.getMsgId());

        // 回复一个离线同步消息 ack，将未同步消息推送给用户
        ConfirmAck ack = ConfirmAck.newBuilder()
                .setAck(Ack.SYNC_VALUE) // 代表此为 sync ack
                .setMessageId(body.getMsgId())
                .setResultCode(ResultCode.OK_VALUE)
                .setData(MessagePack.newBuilder().setData(JSON.toJSONString(offlineMessageDTOList)).build())
                .build();
        ctx.writeAndFlush(ack);
    }
}
