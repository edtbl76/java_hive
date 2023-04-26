package com.java8.forEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class forEachStreamExample {

    /*
        forEach() is a utility method to iterate over a COLLECTION or STREAM and perform a certain action on each
        element.

            (From Iterable.java)

                default void forEach(Consumer<? super T> action) {
                    Objects.requireNonNull(action);
                    for (T t : this) {
                        action.accept(it);
                    }
                }
     */
    public static void main(String[] args) {

        ArrayList<Integer> numList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Consumer<Integer> action = System.out::println;

        numList.stream().filter(n -> n % 2 == 0).forEach(action);
    }
}
