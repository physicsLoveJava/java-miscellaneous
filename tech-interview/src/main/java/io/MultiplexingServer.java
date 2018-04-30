package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class MultiplexingServer {

    public void start() {
        try {
            Selector selector = SelectorProvider.provider().openSelector();
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ServerSocket socket = ssc.socket();
            ssc.configureBlocking(false);
            socket.bind(new InetSocketAddress(8000));
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int n = selector.select();
//                System.out.println("select...");
                if(n == 0) {
                    continue;
                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        SocketChannel sc = channel.accept();
                        int read;
                        while ((read = sc.read(buffer)) != -1) {
                            buffer.flip();
                            System.out.println(new String(buffer.array()));
                            buffer.clear();
                        }
                    }
                    it.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MultiplexingServer().start();
    }

}
