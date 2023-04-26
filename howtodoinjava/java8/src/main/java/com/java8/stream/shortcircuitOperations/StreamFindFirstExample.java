package com.java8.stream.shortcircuitOperations;

import com.java8.stream.coreOperations.getListOfStrings;

public class StreamFindFirstExample {

    /*
        findFirst()
            - ShortCircuit TERMINAL operation.
            - returns first element from  the stream, and then stops processing even if there are further matches
     */
    public static void main(String[] args) {
        String firstMatchedName = getListOfStrings.getMembers().stream()
                .filter((s) -> s.startsWith("J"))
                .findFirst().get();

        System.out.println(firstMatchedName);
    }
}
