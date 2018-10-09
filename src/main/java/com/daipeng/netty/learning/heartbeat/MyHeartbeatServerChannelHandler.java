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
                    //ctx.close(); 建立好的连接，客户端在readIdleTime时间没有给服务器发送消息，服务器主动关闭连接，类似Http1.1 KeepAlive。
                    eventType = "读超时";
                    break;

                case WRITER_IDLE:
                    //ctx.writeAndFlush(new PingMessage()); 建立好的连接, 服务端在超过writeIdleTime时间后，可以主动推送消息告诉客户端，完成重连。
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
