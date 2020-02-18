package com.sinoyang.netty.lession4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 心跳检测
 * 客户端使用：com.sinoyang.netty.lession3.MyChatClient
 */
public class MyServer {

    public static void main(String[] args) {

        // 客户端接收连接，转发给workerGroup
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 类似于死循环
        // 具体的处理。
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // NioSctpServerChannel NioServerSocketChannel
            // 增加日志处理
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new MyServerInitializer());
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