package com.sinoyang.netty.lession3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class MyChatServerHanlder extends SimpleChannelInboundHandler<String> {

    // 用于存放客户端的各个Channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 接收A,B,C三个客户端发来的信息，如果是自己的消息的话，前面加“自己”
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        Channel channel = ctx.channel();
        // 遍历
        channelGroup.forEach(ch -> {
            // 判断是否为自己
            if(channel != ch) {
                // 不是
                ch.writeAndFlush(channel.remoteAddress() + "，发送的消息：" + msg + "\n");
            } else {
                // 是
                ch.writeAndFlush("【自己】" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.fillInStackTrace();
        ctx.close();
    }

    /**
     * 服务链接上
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 广播这个channel已经添加
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "加入！\n");
        // 已经添加好的Channel添加到ChannelGroup
        channelGroup.add(channel);
    }

    /**
     * 链接断掉后触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 广播这个channel已经断掉
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "离开！目前系统还有(" + channelGroup.size() + ")个用户在线！\n");
        // 下面代码会自动调用。
        System.out.println();
        // channelGroup.remove(channel);
    }

    /**
     * 链接处于活跃状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 广播这个channel上线
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "上线！\n");
    }

    /**
     * 下线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 广播这个channel下线
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + "下线！\n");
    }
}