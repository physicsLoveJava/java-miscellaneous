package com.lujian.tech.libs.jetty;

import org.eclipse.jetty.server.Server;

public class ServerStart {

    public static void main(String[] args) {
        Server server = new Server(8000);
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
