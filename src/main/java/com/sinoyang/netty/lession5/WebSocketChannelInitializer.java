package com.sinoyang.netty.lession5;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        // 处理HTTP
        pipeline.addLast("HttpServerCodec", new HttpServerCodec());
        pipeline.addLast("ChunkedWriteHandler", new ChunkedWriteHandler());
        pipeline.addLast("HttpObjectAggregator", new HttpObjectAggregator(8192));
        // WebSocket
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new WebSocketChannelHanlder());
    }
}
