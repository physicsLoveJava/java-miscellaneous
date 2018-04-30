package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NonBlockingServer {

    public void start() {
        try {
            ServerSocketChannel sc = ServerSocketChannel.open().bind(new InetSocketAddress(8000));
            sc.configureBlocking(false);
            while (true) {
                SocketChannel socketChannel = sc.accept();
                System.out.println("accept socket");
                if(socketChannel == null) {
                    Thread.sleep(1000);
                    continue;
                }
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int read;
                while ((read = socketChannel.read(buffer)) != -1) {
                    buffer.flip();
                    System.out.println(new String(buffer.array()));
                    buffer.clear();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new NonBlockingServer().start();
    }

}
