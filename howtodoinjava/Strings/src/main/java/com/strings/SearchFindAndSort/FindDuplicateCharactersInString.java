package com.strings.SearchFindAndSort;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FindDuplicateCharactersInString {

    public static void main(String[] args) {
        System.out.println("Please just type a bunch of characters, preferably with duplicates: ");
        String input = new Scanner(System.in).nextLine();

        // in order to evaluate this, we need to convert it to a character array
        char[] chars = input.toCharArray();

        // Create a HashMap of each Char to an Integer
        Map<Character, Integer> map = new HashMap<>();

        // iterate through the character array.
        // if the value doesnt' exist yet, add it to the HashMap w/ a counter of one.
        // If it already exists, then increment the counter
        for (char c : chars) {

            if (map.containsKey(c)) {
                int counter = map.get(c);
                map.put(c, ++counter);          // pre is important, because we want the NEW value immediately.
            } else {
                map.put(c, 1);
            }
        }

        // iterate through the char set and print the results
        for (char c : map.keySet()) {
            System.out.println(c + ": " + map.get(c));
        }

        // alternatively I could loop through and just Identify the dupes (I'm excluding whitespace by default)
        System.out.println("Duplicate Characters: ");
        for (char c : map.keySet()) {
            if (map.get(c) > 1 && !Character.isWhitespace(c)) {
                System.out.print(c + " ");
            }
        }
        System.out.print("\n");

        System.out.println("Distinct Characters");
        for (char c : map.keySet()) {
            if (map.get(c) == 1) {
                System.out.print(c + " ");
            }
        }
        System.out.println("\n");
    }
}
