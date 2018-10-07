package com.daipeng.netty.learning.boradcast;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description: Chat Server channel handler
 * @Auther: daipeng
 * @Email: daipeng.456@163.com
 * @Date: 2018/10/7 20:13
 */
public class ChatServerChannelHandler extends SimpleChannelInboundHandler<String>{

    /**
     * Note:Single-thread singleton {@link EventExecutor}.  It starts the thread automatically and stops it when there is no
     * task pending in the task queue for 1 second.  Please note it is not scalable to schedule large number of tasks to
     * this executor; use a dedicated executor.
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if(channel == ch){//自己本身的连接
                ch.writeAndFlush("自己发送的消息: " + msg + "\n");
            }else{
                ch.writeAndFlush(ch.remoteAddress() + " 发送的消息：" + msg + "\n");
            }
        });

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

    /**
     *
     * @Description: 建立连接后，连接激活通知
     * @param:
     * @return:
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午9:02
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线");
    }

    /**
     *
     * @Description: 建立连接后，连接非激活通知
     * @param:
     * @return:
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午9:02
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线");
    }

    /**
     *
     * @Description: 建立连接，加入聊天室通知提醒
     * @param:
     * @return:
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午8:18
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "加入聊天室");
        channelGroup.writeAndFlush("服务器消息: " + channel.remoteAddress() + "加入聊天室\n");
        channelGroup.add(channel);
    }
    
    /**
     *
     * @Description: 断开连接，离开聊天室通知提醒
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午8:18
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "离开聊天室" + " Channel Group Size: " + channelGroup.size());
        channelGroup.writeAndFlush("服务器消息: " + channel.remoteAddress() + "离开聊天室\n");
        /**
         * Note: 当client主动关闭连接时，netty会自动将ChannelGroup中channel移除, 无需代码主动移除channel,打印channelGroup.size()可以印证。
         */
        //channelGroup.remove(channel);
    }
}
