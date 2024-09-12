package com.winmanboo.space.im.server.core.starter;

import com.winmanboo.space.im.common.api.constants.TopicNames;
import com.winmanboo.space.im.server.api.protocol.message.Message;
import com.winmanboo.space.im.server.core.codec.decoder.WebSocketProtobufEncoder;
import com.winmanboo.space.im.server.core.codec.decoder.WebSocketRetainDecoder;
import com.winmanboo.space.im.server.core.consume.ConsumePushMessageListener;
import com.winmanboo.space.im.server.core.handler.core.WebSocketNettyServerHandler;
import com.winmanboo.space.im.server.core.prop.SpaceImWebSocketProperties;
import com.winmanboo.space.im.server.core.register.ServerRegister;
import com.winmanboo.space.im.server.core.utils.Hooker;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.NetUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author winmanboo
 * @date 2024/7/28 16:09
 */
@Slf4j
@Component
@EnableConfigurationProperties(SpaceImWebSocketProperties.class)
public class WebSocketStarter implements InitializingBean {

    @Resource
    private SpaceImWebSocketProperties spaceImWebSocketProperties;

    @Resource
    private ServerRegister serverRegister;

    @Resource
    private WebSocketNettyServerHandler webSocketNettyServerHandler;

    @Resource
    private DefaultMQProducer defaultMQProducer;

    @Resource
    private DefaultMQPushConsumer defaultMQPushConsumer;

    @Resource
    private ConsumePushMessageListener consumePushMessageListener;

    void runServer() throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                // 设置服务最大连接队列数量
                .option(ChannelOption.SO_BACKLOG, NetUtil.SOMAXCONN)
                // 地址重用，防止服务挂了端口号依然被占用的情况下其他服务无法使用这个端口
                .option(ChannelOption.SO_REUSEADDR, true)
                // 长连接保活检测，默认 2h 小时检测一次
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        log.info("[WebSocketStarter] 初始化通道");
                        // 由于 websocket 基于 http，因此添加 http 编解码器
                        ch.pipeline().addLast("http-codec", new HttpServerCodec())
                                // 基于 chunk 块的写入，可以写入大数据流
                                .addLast("http-chunk", new ChunkedWriteHandler())
                                // HTTP 解码器在每个 HTTP 消息中会产生多个消息对象，因此通过这个 handler 将多个数据进行聚合
                                // 可以理解为当浏览器在一个请求中发送大量数据时，会产生多个 http 请求
                                .addLast("aggregator", new HttpObjectAggregator(65535))
                                // 使用 websocket 协议处理，参数为访问路径
                                // 内部会处理握手和 websocket 协议的心跳检测
                                // 另外 websocket 的数据都是以 WebsocketFrame 进行传输
                                .addLast("ws-protocol", new WebSocketServerProtocolHandler("/space-ws"))
                                // protobuf 解码器，根据长度字段解码
                                .addLast(new ProtobufVarint32FrameDecoder())
                                // protobuf 编码器，添加长度字段，一般与 ProtobufVarint32FrameDecoder 配套使用
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                // 将 BinaryWebSocketFrame 转为 ByteBuf 以便后续 protobuf 解码器解码
                                .addLast(new WebSocketRetainDecoder())
                                // protobuf 解码器，按消息协议体解码
                                .addLast(new ProtobufDecoder(Message.getDefaultInstance()))
                                // protobuf 编码器
                                .addLast(new WebSocketProtobufEncoder())
                                .addLast(webSocketNettyServerHandler);
                    }
                });

        Hooker.gracefulShutdown(() -> {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        });

        Integer port = spaceImWebSocketProperties.getPort();
        ChannelFuture future = bootstrap.bind(port).sync();
        serverRegister.register();
        log.info("[WebSocketStarter] space-im websocket server start success, server port:{}", port);
        future.channel().closeFuture();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        runServer();
        startProducer();
        startConsumer();
    }

    private void startProducer() throws MQClientException {
        defaultMQProducer.start();
        log.info("[ImServerCoreApplication] run mq producer success!");
    }

    private void startConsumer() throws MQClientException {
        String topic = TopicNames.MSG_PUSH_TOPIC + serverRegister.getCurrentServerAddress().replace(".", "_");
        defaultMQPushConsumer.subscribe(topic, "");
        defaultMQPushConsumer.setMessageListener(consumePushMessageListener);
        defaultMQPushConsumer.start();
        log.info("[ImServerCoreApplication] run mq consumer success!");
    }
}
