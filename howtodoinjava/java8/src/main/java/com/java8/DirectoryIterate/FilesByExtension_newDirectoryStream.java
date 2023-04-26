package com.java8.DirectoryIterate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesByExtension_newDirectoryStream {

    public static void main(String[] args) throws IOException {
        Files.newDirectoryStream(Paths.get("."),
                path -> path.toString().endsWith(".xml"))
                .forEach(System.out::println);
    }
}
