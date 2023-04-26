package com.java8.InternalVsExternalIteration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExternalIterationExample2 {

    // This is just another way to write the example from previous.
    public static void main(String[] args) {
        List<String> alphabets = Arrays.asList("a", "b", "c", "d");

        Iterator<String> iterator = alphabets.listIterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().toUpperCase());
    }
}
