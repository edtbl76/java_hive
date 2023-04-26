package com.java8.Base64;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class WrapOutputStreamToBase64 {

    public static void main(String[] args) {
        Path originalPath = Paths.get(".", "pom.xml");
        Path targetPath = Paths.get(".", "encoded.txt");
        Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
        try (OutputStream output = Files.newOutputStream(targetPath)) {
            // This copies the original file into the target file
            Files.copy(originalPath, mimeEncoder.wrap(output));
            // This creates a stream so we can output it
            OutputStream encodedStream = mimeEncoder.wrap(output);
            System.out.println(encodedStream.toString());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
