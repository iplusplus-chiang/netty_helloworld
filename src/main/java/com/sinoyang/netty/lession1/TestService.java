package com.sinoyang.netty.lession1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * curl http://localhost:8899
 * curl -X POST http://localhost:8899
 *
 * -Dfile.encoding=UTF-8
 */
public class TestService {

    public static void main(String[] args) {

        // 客户端接收连接，转发给workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 类似于死循环
        // 具体的处理。
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // NioSctpServerChannel NioServerSocketChannel
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new TestServerInitializer());
            // 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            // 关闭
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}