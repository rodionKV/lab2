package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Client {
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
