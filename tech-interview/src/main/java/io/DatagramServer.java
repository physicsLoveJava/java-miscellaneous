package io;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DatagramServer {

    public void start() {
        try {
            DatagramChannel dc = DatagramChannel.open();
            DatagramSocket socket = dc.socket();
            socket.bind(new InetSocketAddress(8000));
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                buffer.clear();
                SocketAddress sadd = dc.receive(buffer);
                System.out.println(sadd);
                System.out.println(new String(buffer.array()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DatagramServer().start();
    }

}
