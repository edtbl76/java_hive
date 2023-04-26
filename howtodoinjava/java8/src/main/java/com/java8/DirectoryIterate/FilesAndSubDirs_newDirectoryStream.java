package com.java8.DirectoryIterate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesAndSubDirs_newDirectoryStream {
    /*
        This is faster than the standard Files class.
     */
    public static void main(String[] args) throws IOException {
        Files.newDirectoryStream(Paths.get("."))
                .forEach(System.out::println);
    }
}
