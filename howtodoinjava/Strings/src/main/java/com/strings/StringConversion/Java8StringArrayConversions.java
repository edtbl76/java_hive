package com.strings.StringConversion;

import java.util.Arrays;
import java.util.List;

public class Java8StringArrayConversions {

    public static void main(String[] args) {

        /*
            String.join()
                - we've seen this.
                A common use case is joining XML Elements for parsing.
         */
        String date = String.join("/", "10", "15", "1986");
        System.out.println(date);

        /*
            String join(Char sequence delimiter, Iterable<? extends CharSequence> elements)
            - this is an overriden version used to join an array of string or a list of strings.
         */
        List<String> macAddress = Arrays.asList("00:00:00", "AB", "CD", "EF");
        System.out.println(String.join(":", macAddress));

    }
}
