package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class SessionFileDrop {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    private Client client;
    private Server server;

    public SessionFileDrop(Socket socket) {
        this.socket = socket;
    }

    public SessionFileDrop(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

    public void sendFile(String nameFile, File file) throws IOException {
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();

        ProtocolFileDrop.Header header = new ProtocolFileDrop.Header(client, nameFile, Files.size(file.toPath()));
        ProtocolFileDrop.Data data = new ProtocolFileDrop.Data(file);

        header.write(outputStream);
        data.write(outputStream);
    }
    public void receiveFile() throws IOException, ClassNotFoundException {
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        ProtocolFileDrop.Header header = ProtocolFileDrop.Header.read(inputStream);
        ProtocolFileDrop.Data data = ProtocolFileDrop.Data.read(inputStream);

    }


}
