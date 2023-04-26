package com.strings.Methods;

public class isEmptyExample {

    /*
        boolean isEmpty()
            - returns true if string has length 0.
     */

    public static void main(String[] args) {

        /*
        This is usually used to ensure that there is data in the string before performing an operation that
        would otherwise fail against an empty string.
         */

        System.out.println("".isEmpty());
        System.out.println("Full".isEmpty());
    }
}
