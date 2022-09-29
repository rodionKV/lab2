package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ProtocolFileDrop {

    public static class Header {
        private final Client client;
        private final String nameFile;
        private final Long sizeFile;

        public Header(Client client, String nameFile, Long sizeFile) {
            this.client = client;
            this.nameFile = nameFile;
            this.sizeFile = sizeFile;
        }

        public String getNameFile() {
            return nameFile;
        }

        public Long getSizeFile() {
            return sizeFile;
        }

        public Client getClient() {
            return client;
        }


        public void write(OutputStream outputStream) throws IOException {
            ByteArrayOutputStream outputClientID = new ByteArrayOutputStream();
            byte[] clientID = client.toString().getBytes(StandardCharsets.UTF_8);
            outputClientID.write(clientID.length);
            outputClientID.write(clientID);

            ByteArrayOutputStream outputNameFile = new ByteArrayOutputStream();
            byte[] nameFile = getNameFile().getBytes(StandardCharsets.UTF_8);
            outputNameFile.write(nameFile.length);
            outputNameFile.write(nameFile);

            ByteArrayOutputStream outputSizeFile = new ByteArrayOutputStream();
            byte[] sizeFile = String.valueOf(getSizeFile()).getBytes(StandardCharsets.UTF_8);
            outputSizeFile.write(sizeFile.length);
            outputSizeFile.write(sizeFile);

            outputStream.write(outputClientID.toByteArray());
            outputStream.write(outputNameFile.toByteArray());
            outputStream.write(outputSizeFile.toByteArray());

        }

        public static Header read(InputStream inputStream) throws IOException, ClassNotFoundException {
            int sizeClientName = inputStream.read();
            String clientName = new String(inputStream.readNBytes(sizeClientName), StandardCharsets.UTF_8);
            System.out.println(clientName);

            int sizeFileName = inputStream.read();
            String fileName = new String(inputStream.readNBytes(sizeFileName), StandardCharsets.UTF_8);
            System.out.println(fileName);

            int sizeCountBytes = inputStream.read();
            String countBytes = new String(inputStream.readNBytes(sizeCountBytes), StandardCharsets.UTF_8);
            System.out.println(countBytes);

            return new Header(new Client(clientName), fileName, Long.decode(countBytes));
        }
    }


    public static class Data {
        private File file;

        public Data(File file) {
            this.file = file;
        }

        public static Data read(InputStream inputStream) throws IOException {
            File upload = new File("upload/hello.pdf");
            FileOutputStream outputStream = new FileOutputStream( upload);
            byte[] buffer;
            while ((buffer = inputStream.readNBytes(100)).length != 0) {
                outputStream.write(buffer);
            }
            return new Data(upload);
        }

        public void write(OutputStream outputStream) throws IOException {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] buffer;
            while ((buffer = fileInputStream.readNBytes(100)).length != 0) {
                outputStream.write(buffer);
            }
        }
    }
}
