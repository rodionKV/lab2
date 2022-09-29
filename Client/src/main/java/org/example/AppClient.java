package org.example;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class AppClient {
    String path;
    static String ipserver="10.9.51.239";
    static int port = 3535;
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getByName(ipserver),port);

        SessionFileDrop sessionFileDrop = new SessionFileDrop(socket,new Client("РодионǗ"));
        sessionFileDrop.sendFile("hello.txt",new File("/home/rodion/Документы/книги/JAVA/Java_from_EPAM_Blinov_2020.pdf"));
    }
}