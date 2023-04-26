package com.java8.stream.coreOperations;

import java.util.List;
import java.util.stream.Collectors;

public class StreamCollectExample {

    /*
       COLLECT()
        - method used to receive elements from a stream and store them in a COLLECTION.
     */
    public static void main(String[] args) {
        List<String> membersNames = getListOfStrings.getMembers().stream()
                .sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(membersNames);
    }
}
