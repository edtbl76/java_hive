package com.java8.MethodReferences;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MethodReference_Class_staticMethodName {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> max = integers.stream().reduce(Math::max);
        max.ifPresent(value -> System.out.println(value));
    }
}
