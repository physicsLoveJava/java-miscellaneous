package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramClient {

    public void start() {
        try {
            DatagramChannel dc = DatagramChannel.open();
            dc.send(ByteBuffer.wrap("abcdef".getBytes()), new InetSocketAddress("localhost", 8000));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new DatagramClient().start();
        }
    }

}
