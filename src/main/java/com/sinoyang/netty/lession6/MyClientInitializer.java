package com.sinoyang.netty.lession6;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {

        ChannelPipeline pipeline = ch.pipeline();
        // protobuf 处理器
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        // 设置单个类型，缺点不支持多类型
        //pipeline.addLast(new ProtobufDecoder(MyDataInfo.Person.getDefaultInstance()));
        // 支持多类型。
        pipeline.addLast(new ProtobufDecoder(MyDataInfoUnion.MyMessage.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new MyClientHanlder());
    }
}