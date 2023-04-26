package com.java8.Optionals;

import java.util.NoSuchElementException;
import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {
        Optional<Integer> isntEmpty = Optional.of(5);       // creates a default non-null value
        System.out.println(isntEmpty.isPresent());
        System.out.println(isntEmpty.isEmpty());
        System.out.println(isntEmpty.get());

        Optional<Integer> isEmpty = Optional.empty();       // creates an empty one
        System.out.println(isEmpty.isPresent());
        System.out.println(isEmpty.isEmpty());

        try {
            System.out.println(isEmpty.get());
        } catch (NoSuchElementException nsee) {
            System.out.println(nsee.toString());
        }

    }
}
