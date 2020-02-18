package com.sinoyang.netty.lession5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * 测试websocket
 * 客户端：file:///D:/WorkSpace/netty_helloworld/src/webapp/websocket.html
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
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new WebSocketChannelInitializer());
            // 绑定端口
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
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
