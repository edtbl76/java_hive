package com.emangini.java9.StreamAPIUpdates;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OverloadedStreamIterate_Java9 {

    /*
        CHeck out the Java8 version first.

        This is a more interesting version because predicate logic is a cleaner (and more precise) way of
        managing iteration
            - the predicate allows more flexibility by defining a Predicate that can be passed in to the iterator,


        The previous example is more prone to error, and the "run to infinity, but break on limit" paradigm is a stale
        procedural that binds the iterator to the logic, making it harder to create generic patterns.
     */
    public static void main(String[] args) {
        // Building a custom Predicate object.
        Predicate<Integer> leTen = i -> i <= 10;

        // This demonstrates the flexibility. I have much more control over the way this iterator operates
        // against a stream.
        List<Integer> numbers = Stream.iterate(1, leTen, i -> i + 1).collect(Collectors.toList());
        System.out.println(numbers);
    }
}
