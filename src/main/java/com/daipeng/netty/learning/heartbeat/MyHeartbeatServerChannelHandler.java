package com.daipeng.netty.learning.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Description:心跳服务端处理器
 * @Auther: daipeng
 * @Email: daipeng.456@163.com
 * @Date: 2018/10/8 17:41
 */
public class MyHeartbeatServerChannelHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * @Description: 当有读写事件超时时触发执行
     * @param: [ctx, evt]
     * @return: void
     * @auther: daipeng
     * @email: daipeng.456@163.com
     * @date: 2018/10/8 下午6:11
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = "";
            switch (event.state()){
                case READER_IDLE:
                    eventType = "读超时";
                    break;

                case WRITER_IDLE:
                    eventType = "写超时";
                    break;

                case ALL_IDLE:
                    eventType = "读写超时";
                    break;

                default: break;
            }
            System.out.println("From Client：" + ctx.channel().remoteAddress() + " " + eventType);
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
