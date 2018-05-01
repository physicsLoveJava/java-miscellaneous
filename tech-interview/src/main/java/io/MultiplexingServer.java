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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
                ExecutorService service = Executors.newFixedThreadPool(100);
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                        final SocketChannel sc = channel.accept();
                        service.submit(new Runnable() {
                            @Override
                            public void run() {
                                ByteBuffer buffer = ByteBuffer.allocate(1024);
                                try {
                                    while (sc.read(buffer) != -1) {
                                        buffer.flip();
                                        System.out.println(new String(buffer.array()));
                                        buffer.clear();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
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
