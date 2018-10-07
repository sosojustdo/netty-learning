package com.daipeng.netty.learning.httpserver;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 *
 * @Description: 定义HttpServer初始化器
 * @auther: daipeng
 * @email: daipeng.456@163.com
 * @date: 2018/10/7 下午1:55
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast("httpServerCodec", new HttpServerCodec());
        channelPipeline.addLast("testServerChannelHandler", new TestHttpServerChannelHandler());
    }
}
