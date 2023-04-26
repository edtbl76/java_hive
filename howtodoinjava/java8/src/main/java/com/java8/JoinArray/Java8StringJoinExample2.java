package com.java8.JoinArray;

import java.util.Arrays;
import java.util.List;

public class Java8StringJoinExample2 {

    /*
        String join(CharSequence delimiter, Iterable<? extends CharSequence> elements)
     */
    public static void main(String[] args) {
        List<String> strList = Arrays.asList("Huey", "Dewey", "Louey");
        String joinedString = String.join(", ", strList);
        System.out.println(joinedString);
    }
}
