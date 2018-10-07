package com.daipeng.netty.learning.socket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @Description: Socket Channel Handler处理器
 * @Auther: daipeng
 * @Email: daipeng.456@163.com
 * @Date: 2018/10/7 14:40
 */
public class MyServerChannelHandler  extends SimpleChannelInboundHandler<String>{


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //打印客户端发来的消息内容
        System.out.println("Client: " + ctx.channel().remoteAddress() + "发来一条消息: " + msg);
        //服务器收到服务端消息后，回应客户端一条uuid的消息内容
        ctx.writeAndFlush("from Server: 回应Client uuid消息内容" + UUID.randomUUID().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
