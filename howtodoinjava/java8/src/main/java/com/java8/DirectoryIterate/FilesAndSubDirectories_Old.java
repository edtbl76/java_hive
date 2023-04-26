package com.java8.DirectoryIterate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesAndSubDirectories_Old {

    public static void main(String[] args) throws IOException {

        // lists files and subdirectories of given Path
        try {
            Files.list(Paths.get(".")).forEach(System.out::println);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
