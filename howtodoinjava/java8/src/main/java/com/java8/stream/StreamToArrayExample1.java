package com.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamToArrayExample1 {

    public static void main(String[] args) {

        // Create List of Ints and populate it from a for loop
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++)
            list.add(i);

        // Convert it to a stream
        Stream<Integer> stream = list.stream();

        // filter stream for just even Ints and then assign it to an Integer Array
        Integer[] evenNumbers = stream.filter(integer -> integer % 2 == 0).toArray(Integer[]::new);
        System.out.println(Arrays.toString(evenNumbers));   // You don't need the Arrays.toString() wrap, Systems.out.println
                                                            // will implicitly cast the array to String.

    }
}
