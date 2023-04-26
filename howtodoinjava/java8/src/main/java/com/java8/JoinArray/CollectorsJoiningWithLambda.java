package com.java8.JoinArray;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsJoiningWithLambda {

    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");

        // collect() is really only necessary if you want to add a prefix or suffix.
        String joinedString = numbers.stream().collect(Collectors.joining(", ", "[", "]"));
        System.out.println(joinedString);
    }
}
