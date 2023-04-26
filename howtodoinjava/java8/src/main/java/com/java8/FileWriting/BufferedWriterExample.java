package com.java8.FileWriting;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BufferedWriterExample {

    /*
        BUFFEREDWRITER
            - used to write text to a character/byte stream.

            - before printing the data, it stores characters in buffer and prints them in bunches.
            - w/o that buffering, each invocation of print() would cause the chars to be converted into bytes that would
            then be IMMEDIATELY written to the file.
                - super slow/inefficient.
     */
    public static void main(String[] args) {
        Path path = Paths.get("./output.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("Hello World!!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
