package com.java8.stream.coreOperations;

public class StreamMatchExample {

    /*
        MATTCH
            - matching operations can be used to check whether a certain PREDICATE matches the stream.
            - ALL of these are TERMINAL and return a BOOLEAN
     */
    public static void main(String[] args) {
        // Do any match
        boolean matchedResult = getListOfStrings.getMembers().stream()
                .anyMatch((s) -> s.startsWith("J"));
        System.out.println(matchedResult);

        // do all match
        matchedResult = getListOfStrings.getMembers().stream()
                .allMatch((s) -> s.startsWith("J"));
        System.out.println(matchedResult);

        // do none match
        matchedResult = getListOfStrings.getMembers().stream()
                .noneMatch((s) -> s.startsWith("J"));
        System.out.println(matchedResult);
    }
}
