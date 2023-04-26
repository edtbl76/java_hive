package com.java8.InternalVsExternalIteration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InternalIterationExample {

    // See external iteration first
    /*
        INTERNAL ITERATION
        - this allows the client to provide the WHAT (in this case the alphabet letters)
        - but the LIBRARY provides the HOW.

            - client code is cleaner, because it only needs to focus on stating the problem, not going about
            the details of solving it.
            - complex optimization code can live in the libraries, where it benefits all users.
     */
    public static void main(String[] args) {
        List<String> alphas = Arrays.asList("x", "y", "z");
        alphas.forEach(l -> System.out.println(l.toUpperCase()));
    }
}
