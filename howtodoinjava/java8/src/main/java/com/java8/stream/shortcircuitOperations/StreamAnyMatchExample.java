package com.java8.stream.shortcircuitOperations;

import com.java8.stream.coreOperations.getListOfStrings;

public class StreamAnyMatchExample {

    /*
        ANYMATCH
            - returns true once a condition passed as predicate is satsified
            - this is a short-circuit operation because it doesn't process any more elements after it matches ONCE
     */
    public static void main(String[] args) {
        boolean matched = getListOfStrings.getMembers().stream()
                .anyMatch((s) -> s.startsWith("J"));

        System.out.println(matched);
    }
}
