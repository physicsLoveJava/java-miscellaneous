package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class AsynchronousServer {

    public void start() {
        try {
            final AsynchronousServerSocketChannel async = AsynchronousChannelProvider.provider().openAsynchronousServerSocketChannel(
                    AsynchronousChannelGroup.withFixedThreadPool(10,
                            new ThreadFactory() {
                                @Override
                                public Thread newThread(Runnable r) {
                                    return new Thread(r);
                                }
                            }
                    ));
            async.bind(new InetSocketAddress(8000));
            async.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {
                    try {
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        Future<Integer> future = result.read(buffer);
                        System.out.println(future.get());
                        System.out.println(new String(buffer.array()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } finally {
                        async.accept(null, this);
                    }
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println(exc.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new AsynchronousServer().start();
    }

}
