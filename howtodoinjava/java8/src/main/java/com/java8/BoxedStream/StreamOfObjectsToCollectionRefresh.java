package com.java8.BoxedStream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOfObjectsToCollectionRefresh {

    /*
        We can convert a stream of OBJECTS to a Collection, but this won't work w/ primitives.
     */
    public static void main(String[] args) {
        List<String> strings = Stream.of("list", "of", "strings").collect(Collectors.toList());
        System.out.println(strings);
    }
}
