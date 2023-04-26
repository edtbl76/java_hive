package com.java8.stream.coreOperations;

public class StreamMapExample {

    /*
        MAP
            - INTERMEDIATE operation converts each element into another object via a specified function
     */
    public static void main(String[] args) {
        getListOfStrings.getMembers().stream().filter(
                (s) ->  s.startsWith("J"))
                .map(String::toUpperCase)
                .forEach(System.out::println);


    }
}
