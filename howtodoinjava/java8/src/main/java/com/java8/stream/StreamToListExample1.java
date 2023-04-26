package com.java8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamToListExample1 {

    public static void main(String[] args) {

        // Create a list of Ints and populate it.
        List<Integer> list = new ArrayList<>();
        for (int i =1; i < 10; i++)
            list.add(i);

        // convert list to Int Stream
        Stream<Integer> stream = list.stream();

        // Use Collectors.toList() and filter to get just the even numbers and store them in a List of Ints
        List<Integer> evenNumbers = stream.filter(integer -> integer % 2 == 0).collect(Collectors.toList());

        // print it out.
        System.out.println(evenNumbers);

    }
}

