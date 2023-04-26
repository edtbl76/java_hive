package com.java8.stream.coreOperations;

public class StreamCountExample {

    /*
        COUNT
        - TERMINAL operation that returns the # of elements in the stream as a LONG
     */
    public static void main(String[] args) {
        long totalMatched = getListOfStrings.getMembers().stream()
                .filter((s) -> s.startsWith("J"))
                .count();

        System.out.println(totalMatched);
    }
}
