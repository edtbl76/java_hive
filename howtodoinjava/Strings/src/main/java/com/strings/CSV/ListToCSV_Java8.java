package com.strings.CSV;

import java.util.Arrays;
import java.util.List;

public class ListToCSV_Java8 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "Joe Satriani",
                "Steve Vai",
                "Eric Johnson"
        );

        // CSV
        System.out.println(String.join(",", list));

        // Space Separated
        System.out.println(String.join(" ", list));

        // Example of building directories
        System.out.println("/" + String.join("/", "usr", "local", "bin"));
    }
}
