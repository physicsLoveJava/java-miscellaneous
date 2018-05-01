package com.lujian.tech.libs.netty;

import org.jboss.netty.channel.*;

public class EchoChannelHandler extends SimpleChannelHandler {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("messageReceived");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        ctx.getChannel().write("hi bro");
        super.messageReceived(ctx, e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelOpen");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        super.channelOpen(ctx, e);
    }

    @Override
    public void channelBound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelBound");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        super.channelBound(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelBound");
        super.channelConnected(ctx, e);
    }

    @Override
    public void channelInterestChanged(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelInterestChanged");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        super.channelInterestChanged(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelUnbound(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelUnbound");
        super.channelUnbound(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        super.channelClosed(ctx, e);
    }

    @Override
    public void writeComplete(ChannelHandlerContext ctx, WriteCompletionEvent e) throws Exception {
        System.out.println("writeComplete");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        super.writeComplete(ctx, e);
    }

    @Override
    public void childChannelOpen(ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception {
        System.out.println("childChannelOpen");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        super.childChannelOpen(ctx, e);
    }

    @Override
    public void childChannelClosed(ChannelHandlerContext ctx, ChildChannelStateEvent e) throws Exception {
        System.out.println("childChannelClosed");
        System.out.println("ctx = [" + ctx + "], e = [" + e + "]");
        super.childChannelClosed(ctx, e);
    }
}
