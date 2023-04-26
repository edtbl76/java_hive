package com.java8.Optionals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalNullableExample {

    public static void main(String[] args) {
        Optional<Integer> optional1 = Optional.ofNullable(null);
        Optional<Integer> optional2 = Optional.ofNullable(5);

        List<Optional<Integer>> list = Arrays.asList(optional1, optional2);

        list.forEach(value -> value.ifPresent(System.out::println));
    }
}
