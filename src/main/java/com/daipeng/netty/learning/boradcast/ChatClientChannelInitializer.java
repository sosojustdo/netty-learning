package com.daipeng.netty.learning.boradcast;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


/**
 * @Description: Chat Client初始化器
 * @Auther: daipeng
 * @Email: daipeng.456@163.com
 * @Date: 2018/10/7 20:04
 */
public class ChatClientChannelInitializer extends ChannelInitializer<SocketChannel>{


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();

        channelPipeline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        channelPipeline.addLast(new ChatClientChannelHandler());
    }

}
