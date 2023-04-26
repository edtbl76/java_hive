package com.java8.stream;

import java.util.stream.Stream;

public class StreamOfExample2_arrayofElements {

    public static void main(String[] args) {
        // This method is NOT recommended. Use the method in Example1
        Stream<Integer> stream = Stream.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        stream.forEach(System.out::println);
    }
}
