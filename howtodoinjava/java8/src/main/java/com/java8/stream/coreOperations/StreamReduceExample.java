package com.java8.stream.coreOperations;

import java.util.Optional;

public class StreamReduceExample {

    /*
        REDUCE
            - TERMINAL operation that performs a REDUCTION on elements of the stream w/ the given
            function
            - result is an Optional holding the reduced value
     */
    public static void main(String[] args) {
        Optional<String> reduced = getListOfStrings.getMembers().stream()
                .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);
    }
}
