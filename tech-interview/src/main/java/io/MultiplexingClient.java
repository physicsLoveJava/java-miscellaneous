package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * name just for matching
 */
public class MultiplexingClient {

    public void start() {
        try {
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress(8000));
            sc.write(ByteBuffer.wrap("ping".getBytes()));
            sc.shutdownInput();
            sc.shutdownOutput();
            sc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(100);
        final AtomicInteger failedCount = new AtomicInteger();
        for (int i = 0; i < 1000; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        new MultiplexingClient().start();
                    }catch (Exception e) {
                        failedCount.incrementAndGet();
                    }
                }
            });
        }
        service.shutdown();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(failedCount);
    }

}
