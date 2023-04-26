package com.java8.MethodReferences;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodReference_Class_instanceMethodName {

    public static void main(String[] args) {

        List<String> strings = Arrays.asList("list","of","strings");

        // Lambda Version
        List<String> sorted = strings.stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());
        System.out.println(sorted);

        // Method Reference version
        List<String> sortedMR = strings.stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());
        System.out.println(sortedMR);

    }
}
