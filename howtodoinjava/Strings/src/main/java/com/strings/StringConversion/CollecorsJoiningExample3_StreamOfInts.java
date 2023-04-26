package com.strings.StringConversion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollecorsJoiningExample3_StreamOfInts {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        String joined = numbers.stream()
                .map(String::valueOf)
                //.map(number -> String.valueOf(number))    <- lambda version. I prefer static method reference.
                .collect(Collectors.joining("..."));

        System.out.println(joined);
    }
}
