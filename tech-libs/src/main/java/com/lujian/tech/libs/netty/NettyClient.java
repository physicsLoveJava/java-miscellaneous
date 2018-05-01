package com.lujian.tech.libs.netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        ClientBootstrap client = new ClientBootstrap();
        client.setFactory(new NioClientSocketChannelFactory());
        client.setPipeline(Channels.pipeline(
                new StringDecoder(),
                new StringEncoder(),
                new EchoClientChannelHandler()
        ));
        ChannelFuture future = client.connect(new InetSocketAddress("127.0.0.1", 8000));
        Channel channel = future.getChannel();
        while (true) {
            channel.write("i am client");
            Thread.sleep(1000);
        }
    }

}
