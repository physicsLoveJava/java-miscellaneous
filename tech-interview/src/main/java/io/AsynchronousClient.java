package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class AsynchronousClient {

    public void start() {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress(8000));
            sc.write(ByteBuffer.wrap("hello".getBytes()));
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new NonBlockingClient().start();
        }
    }

}
