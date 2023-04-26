package com.java8.stream.coreOperations;

public class StreamForEachExample {

    /*
        FOREACH
            - TERMINAL operation
            - helps in iterating over all elements of stream and then performing some operation on each of them.
            - usually passed in as a Lambda Expression Parameter or a Static Method Reference
     */
    public static void main(String[] args) {
        getListOfStrings.getMembers()
                .forEach(System.out::println);
    }
}
