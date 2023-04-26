package com.emangini.java9.StreamAPIUpdates;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class takeWhileDropWhileExample2_Unordered {

    // demonstrates unordered results.
    public static void main(String[] args) {
        Predicate<String> predicate = s -> !s.equals("d");
        List<String> alpha = List.of("b", "d", "a", "g", "c", "h");

        List<String> subset1 = alpha
                .stream()
                .takeWhile(predicate)
                .collect(Collectors.toList());

        List<String> subset2 = alpha
                .stream()
                .dropWhile(predicate)
                .collect(Collectors.toList());

        System.out.println("Original   : " + alpha);
        System.out.println("takeWhile(): " + subset1);
        System.out.println("dropWhile(): " + subset2);
    }
}
