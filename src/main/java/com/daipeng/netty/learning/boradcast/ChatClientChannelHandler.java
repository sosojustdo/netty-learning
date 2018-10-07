package com.daipeng.netty.learning.boradcast;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description: Chat Client channel handler
 * @Auther: daipeng
 * @Email: daipeng.456@163.com
 * @Date: 2018/10/7 20:13
 */
public class ChatClientChannelHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

    /**
     *
     * @Description: pipeline异常处理
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午8:17
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
