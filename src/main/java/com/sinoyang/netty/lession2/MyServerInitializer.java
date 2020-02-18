package com.sinoyang.netty.lession2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 *
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {

        ChannelPipeline pipeLine = ch.pipeline();
        pipeLine.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeLine.addLast(new LengthFieldPrepender(4));
        // 添加编码集
        pipeLine.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeLine.addLast(new StringEncoder(CharsetUtil.UTF_8));
        // 添加用户自定义响应
        pipeLine.addLast(new MyServerHanlder());
    }
}