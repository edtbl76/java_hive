package com.emangini.java9.InterfacePrivateMethods.CalculatorExample;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public interface CustomCalculator {

    /*
        remember that the ELLIPSIS in Java allows you to accept a variable number of arguments.
     */


    default int addEvenNumbers(int... nums) {
        return add(n -> n % 2 == 0, nums);
    }

    default int addOddNumbers(int... nums) {
        return add(n -> n % 2 == 1, nums);
    }

    // Here is our private non-static method that is being shared w/ the two default methods.
    private int add(IntPredicate predicate, int... nums) {
        return IntStream.of(nums)
                .filter(predicate)
                .sum();
    }
}
