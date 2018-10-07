package com.daipeng.netty.learning.boradcast;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * @Description: Chat Client
 * @Auther: daipeng
 * @Email: daipeng.456@163.com
 * @Date: 2018/10/7 14:50
 */
public class ChatClient {

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup).channel(NioSocketChannel.class).handler(new ChatClientChannelInitializer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Channel channel = channelFuture.channel();
            while(true) channel.writeAndFlush(br.readLine() + "\r\n");
        }finally {
            bossGroup.shutdownGracefully();
        }
    }

}
