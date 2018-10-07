package com.daipeng.netty.learning.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 *
 * @Description: 定义服务器接口请求处理器
 * @auther: daipeng
 * @email: daipeng.456@163.com
 * @date: 2018/10/7 下午1:55
 */
public class TestHttpServerChannelHandler extends SimpleChannelInboundHandler<HttpObject>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){

            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            HttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

            httpResponse.headers().add("Content/type", "text/plain").add("Content-length", byteBuf.readableBytes());

            ctx.writeAndFlush(httpResponse);
            ctx.channel().close();
        }
    }
    
    /**
     *
     * @Description: 在处理过程中ChannelPipeline发生了异常
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:08
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     *
     * @Description: 当一个ChannelHandler被载入ChannelPipeline的时候触发
     * @param:
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:12
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("add invoked");
    }

    /**
     *
     * @Description: 当一个ChannelHandler从ChannelPipeline中移除的时候触发
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:13
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println("removed invoked");
    }

    /**
     *
     * @Description: 当一个Channel被激活是调用执行
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:13
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("active invoked");
    }
    
    /**
     *
     * @Description: 当一个Channel已经处于非激活的状态且不再连接到远程端的时候被调用执行
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:14
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("inactive invoked");
    }

    /**
     *
     * @Description: 当一个Channel被注册到EventLoop上的时候并且能够处理IO的时候调用执行
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:14
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        System.out.println("channel register invoked");
    }
    
    /**
     *
     * @Description: 当一个Channel从EventLoop中注销的时候且不能再处理I/O的时候调用执行
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:14
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        System.out.println("channel unregister invoked");
    }

    /**
     *
     * @Description: 当数据已经从Channel读取的时候执行
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:16
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        System.out.println("channel read process invoked");
    }
    
    /**
     *
     * @Description: 当一个Channel的读操作已经准备好的时候被调用执行
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:17
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        System.out.println("channel ready read data invoked");
    }
    
    /**
     *
     * @Description: 当一个Channel的可写的状态发生改变的时候执行
     * @param: 
     * @return: 
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/7 下午2:17
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        System.out.println("channel write status change invoked");
    }
}