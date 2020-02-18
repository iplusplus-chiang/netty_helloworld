package com.sinoyang.netty.lession2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 *
 */
public class MyClientHanlder extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        // 获取服务器数据
        System.out.println("Server -> Client : " + channel.remoteAddress() + " : " + msg);
        // 向服务器发送数据
        ctx.writeAndFlush(String.valueOf(new Date().getTime()));
    }

    /**
     * 客户端发送一条数据
     *
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("hello from client!!!");
    }

    /**
     * 出现异常的时候
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.fillInStackTrace();
        ctx.close();
    }
}