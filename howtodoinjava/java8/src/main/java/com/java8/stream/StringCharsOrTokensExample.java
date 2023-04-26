package com.java8.stream;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCharsOrTokensExample {

    public static void main(String[] args) {

        IntStream stream1 = "12345_abcde".chars();
        stream1.forEach(System.out::println);

        //OR
        Stream<String> stream2 = Stream.of("A$B$C".split("\\$"));
        stream2.forEach(System.out::println);
    }
}
