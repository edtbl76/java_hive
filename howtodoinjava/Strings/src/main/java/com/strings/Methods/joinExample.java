package com.strings.Methods;

import java.util.ArrayList;
import java.util.Arrays;

public class joinExample {

    public static void main(String[] args) {
        /*
            static String join()
                - joins given strings using specified delimeter and returns concatenated Java string literal
         */

        // Hyphenating a name or transforming AND concatenating strings.
        String maidenName = "Bray";
        String marriedName = "Sellers";
        System.out.println(String.join("-", maidenName, marriedName));

        // Assembling IP address from an array, by converting the ArrayList<String> in as an Iterable
        Iterable<String> octets = Arrays.asList("192", "168", "1", "1");
        System.out.println(String.join(".", octets));
    }
}
