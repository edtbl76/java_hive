package com.emangini.java11.CollectionToArray;

import java.util.Arrays;
import java.util.List;

public class Example {

    public static void main(String[] args) {

        // Collection
        List<String> names = Arrays.asList("Bob", "Christine", "Linda", "Ed");

        // Arrays Pre Java 11
        String[] namesArrBefore = names.toArray(new String[names.size()]);

        // After
        String[] namesArrAfter = names.toArray(String[]::new);

    }
}
