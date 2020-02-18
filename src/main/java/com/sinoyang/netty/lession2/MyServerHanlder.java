package com.sinoyang.netty.lession2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * 用户自定义响应
 */
public class MyServerHanlder extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        System.out.println("Client -> Server : " + channel.remoteAddress() + " : " + msg);
        // 像客户端相应一条数据
        channel.writeAndFlush(UUID.randomUUID().toString());
    }

    /**
     * 出现异常的时候
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.fillInStackTrace();
        ctx.close();
    }
}
