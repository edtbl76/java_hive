package com.strings.SearchFindAndSort;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SortStringCharsAlphabetically {

    public static void main(String[] args) {

        // INPUT
        System.out.println("Enter a string of characters: ");
        String input = new Scanner(System.in).nextLine();

        // Method 1: Java 8 Streams API
        /*
            - first we split it into an array of Strings using split.
            - that pumps into the Stream, which we sort and join back together as a string.
         */
        String sortedChars = Stream.of(input.split(""))
                .sorted()
                .collect(Collectors.joining());

        System.out.println(sortedChars);

        // Method 2 (older) Use Arrays.sort()
        /*
            The steps are pretty much the same. Convert the string to a char array, sort it and then convert it back to
            a string.
         */
        char[] chars = input.toCharArray();
        Arrays.sort(chars);
        System.out.println(chars);      // No need to use valueOf()



    }
}
