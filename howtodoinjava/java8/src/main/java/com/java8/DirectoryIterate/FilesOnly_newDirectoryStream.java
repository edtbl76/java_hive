package com.java8.DirectoryIterate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesOnly_newDirectoryStream {

    public static void main(String[] args) throws IOException {
        Files.newDirectoryStream(Paths.get("."), path -> path.toFile().isFile())
                .forEach(System.out::println);
    }
}
