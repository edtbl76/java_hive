package com.java8.stream.coreOperations;

public class StreamSortedExample {

    /*
        SORTED
        - INTERMEDIATE operation that returns a sorted view of the stream
        - NOTE: sorted in natural lexicographical order unless you pass in a custom COMPARATOR
     */
    public static void main(String[] args) {
        getListOfStrings.getMembers()
                .stream()
                .sorted()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
