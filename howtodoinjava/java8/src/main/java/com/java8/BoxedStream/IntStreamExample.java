package com.java8.BoxedStream;

import com.java8.stream.ListStreamExample;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamExample {

    /*
        IntStream = a stream of ints.
            - BOXED swtream that converts an int Stream into a List<Integer>
     */
    public static void main(String[] args) {

        // Collection -> Stream
        List<Integer> ints = IntStream.of(1,2,3,4,5)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(ints);

        // Stream operations directly
        Optional<Integer> max = IntStream.of(1,2,3,4,5)
                .boxed()
                .max(Integer::compareTo);

        System.out.println(max.get());
    }
}
