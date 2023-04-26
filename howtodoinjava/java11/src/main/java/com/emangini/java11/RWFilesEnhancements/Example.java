package com.emangini.java11.RWFilesEnhancements;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Example {

    public static void main(String[] args) throws IOException {

        // Write string to file.
        Path tmp = Path.of(File.createTempFile("tempFile", ".tmp").toURI());
        Path returnedFile = Files.writeString(tmp, "Hello World", Charset.defaultCharset(), StandardOpenOption.WRITE);
        System.out.println("Wrote: " + returnedFile);

        String content = Files.readString(Path.of(String.valueOf(returnedFile)), Charset.defaultCharset());
        System.out.println("Read: " + content);
    }
}
