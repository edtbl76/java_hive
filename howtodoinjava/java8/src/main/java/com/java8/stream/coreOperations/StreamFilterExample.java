package com.java8.stream.coreOperations;

public class StreamFilterExample {

    public static void main(String[] args) {
        /*
            FILTER:
                - accepts a predicate to filter all elements of the stream.
                - INTERMEDIATE option allowing us to pipeline to another stream operation (i.e. forEach())
         */
        getListOfStrings.getMembers().stream().filter(
                (s) -> s.startsWith("J"))
                .forEach(System.out::println);
    }
}
