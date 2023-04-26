package com.java8.FileWriting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesWriteExample {

    public static void main(String[] args) throws IOException {
        String content = "Hello world!";
        Files.write(Paths.get("output2.txt"), content.getBytes());
    }
}
