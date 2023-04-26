package com.java8.forEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class forEachListExample {

    public static void main(String[] args) {
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

        Consumer<Integer> action = System.out::println;

        // this is more simple than the forEach Stream. we just print out each element
        numberList.forEach(action);
    }
}
