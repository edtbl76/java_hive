package com.emangini.java9.StreamAPIUpdates;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OverloadedStreamIterate_Java8 {

    /*
        iterate() methods used for creating streams
            - start w/ single element (the seed)
            - subsequent elements are produced by successive applications of the unary operator.
            - RESULT = infinite stream.

        terminating iterator:
            - requires limit/short-circuit function
                - findFirst, findAny, etc.

        Java8 iterate()

            static Stream iterate(final T seed, final UnaryOperator f)

        Java9 iterate()

            static Stream iterate(T seed, Predicate hasNext, UnaryOperator next)
     */
    public static void main(String[] args) {
        List<Integer> numbers = Stream.iterate(1, i -> i + 1)
                .limit(10)
                .collect(Collectors.toList());

        System.out.println(numbers);
    }
}
