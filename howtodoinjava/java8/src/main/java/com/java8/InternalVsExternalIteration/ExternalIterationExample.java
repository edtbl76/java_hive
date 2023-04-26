package com.java8.InternalVsExternalIteration;

import java.util.Arrays;
import java.util.List;

public class ExternalIterationExample {

    /*
        Up until Java7, COLLECTIONS relied on EXTERNAL ITERATION

        EXTERNAL ITERATION:
            - where a Collection provides a means to enumerate its elements by implementing an iterable.
            - clients use this iterator to step sequentially through elements of a collection

        This mixes the WHAT as well as the HOW.
            - WHAT (uppercase)
            - HOW (for loop iterator)
     */
    public static void main(String[] args) {
        List<String> alphas = Arrays.asList("a", "b", "b", "d");

        for (String letter: alphas)
            System.out.println(letter.toUpperCase());
    }
}
