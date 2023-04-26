package com.strings.CSV;

import java.util.Arrays;
import java.util.List;

public class CSVToImmutableArrayListExample {

    public static void main(String[] args) {

        String csv = "heading1, heading2, heading3";

        // Remove whitespace and split by comma (using \\s*,\\s*  regex pattern
        /*
            NOTE: This ArrayList is a fixed-size unmodifiable READ-ONLY list.
            - can't add/remove elements. If you want to make changes, do somethiing else.
         */
        List<String> result = Arrays.asList(csv.split("\\s*,\\s"));
        System.out.println(result);
    }
}
