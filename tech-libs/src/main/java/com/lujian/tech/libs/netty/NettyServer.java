package com.lujian.tech.libs.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;

public class NettyServer {

    public static void main(String[] args) {
        ServerBootstrap boot = new ServerBootstrap();
        boot.setFactory(new NioServerSocketChannelFactory());
        boot.setPipeline(Channels.pipeline(
                new StringDecoder(),
                new EchoChannelHandler()));
        boot.bind(new InetSocketAddress(8000));
    }

}
