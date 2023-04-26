package com.strings.FileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFileToStringExample {

    public static void main(String[] args) {

        String fileName = "./example.txt";

        System.out.println(readLineByLineJava8(fileName));
        System.out.println(readAllBytesJava7(fileName));
        System.out.println(thisShitIsOld(fileName));
    }


    // METHOD 1: Java 8 - Read file line by line using Files.lines(Path path, Charset cs)
    private static String readLineByLineJava8(String filePath) {
        StringBuilder builder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s).append("\n"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return builder.toString();
    }

    // METHOD 2: Java7+ - Files.readAllBytes(Path path)
    private static String readAllBytesJava7(String filePath) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        }  catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return content;
    }

    // METHOD 3 - Below Java7 (YUCK) - BufferedReader and FileReader
    private static String thisShitIsOld(String filePath) {
        StringBuilder  builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
           String currentLine;
           while ((currentLine = reader.readLine()) != null) {
               builder.append(currentLine).append("\n");
           }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return builder.toString();
    }
}
