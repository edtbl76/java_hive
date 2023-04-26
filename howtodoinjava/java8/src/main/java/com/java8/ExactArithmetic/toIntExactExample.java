package com.java8.ExactArithmetic;

public class toIntExactExample {

    /*
        This demonstrates a handy way to use Math exact values to prevent incorrect results due to
        overflows.

     */
    public static void main(String[] args) {

        // This prints the Max Value for a Long
        System.out.println(Long.MAX_VALUE);

        // This converts it to an int and results in -1 (because of the Overflow)
        System.out.println((int)Long.MAX_VALUE);

        // This provides an exact value.
        System.out.println(Math.toIntExact(100_000_000));

        // This can't properly provided an Int, because of the overflow, so it throws an int overflow ArithmeticException
        try {
            System.out.println(Math.toIntExact(Long.MAX_VALUE));
        } catch (ArithmeticException ae) {
            System.out.println(ae.toString());
        }
    }
}
