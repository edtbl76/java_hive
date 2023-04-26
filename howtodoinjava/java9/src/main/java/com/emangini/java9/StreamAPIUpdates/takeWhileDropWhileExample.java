package com.emangini.java9.StreamAPIUpdates;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class takeWhileDropWhileExample {

    /*
        Info.md has more information on takeWhile() and dropwhile().
        - these are ways to limit streams so that we only  get portions of streams based on a predicate.

        - The behavior varies based on the stream structure.
            - ordered streams are essentially split between matching/not-matching the predicate in its entirey
            - un-ordered streams don't match ALL of the elements.
     */
    public static void main(String[] args) {
        // First create a Predicate Object.
        // I'm doing this so I can reference the Predicate in the takeWhile and dropWhile methods.
        Predicate<String> dee = s -> !s.equals("d");


        List<String> alphabets = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i");
        List<String> subset1 = alphabets
                .stream()
                .takeWhile(dee)
                .collect(Collectors.toList());

        List<String> subset2 = alphabets
                .stream()
                .dropWhile(dee)
                .collect(Collectors.toList());


        System.out.println("takeWhile(), prints up to Predicate: " + subset1);
        System.out.println("dropWhile(), prints everything left out of predicate: " + subset2);
    }
}
