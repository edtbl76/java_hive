package com.strings.Methods;

public class internExample {

    /*
        String intern()
            - searches the specified string in memory pool.
                - if found, returns its references
                - else, allocates the string literal in string pool and returns the reference.
     */
    public static void main(String[] args) {

        String example = "Hello";
        System.out.println(example.intern());
        System.out.println("Hello".intern());
    }
}
