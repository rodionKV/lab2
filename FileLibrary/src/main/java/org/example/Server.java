package org.example;

import java.net.InetAddress;

public class Server {
    InetAddress inetAddress;
    int port;

    public Server(InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
    }

}
