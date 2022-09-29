package org.example;

import java.io.IOException;
import java.net.ServerSocket;

public class AppServer {
    int port;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3535);

           SessionFileDrop sessionFileDrop = new SessionFileDrop(serverSocket.accept());
           sessionFileDrop.receiveFile();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}