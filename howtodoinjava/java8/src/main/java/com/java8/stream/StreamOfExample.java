package com.java8.stream;

import java.util.stream.Stream;

public class StreamOfExample {

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        stream.forEach(System.out::println);    // You can use a static method reference: <-- I prefer this.
        // stream.forEach(p -> System.out.println(p)); // or a lambda
    }
}
