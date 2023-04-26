package com.java8.stream.parallelismAndJavaStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelStreamExample {

    /*
        calling parallelStream() instead of stream() will use multiple threads in parallel cores automatically.
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++)
            list.add(i);

        Stream<Integer> stream = list.parallelStream();
        Integer[] evenNumbers = stream.filter(integer -> integer % 2 == 0).toArray(Integer[]::new);
        System.out.println(Arrays.toString(evenNumbers));
    }
}
