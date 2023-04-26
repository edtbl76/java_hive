package com.strings.StringConversion;

public class StringArrayToString {

    /*
        String.join()
            - takes two arguments (delimiter, arrayElements)

     */
    public static void main(String[] args) {
        String[] blues = {"Steve", "Ray", "Vaughan"};

        System.out.println(String.join("", blues));     // no space
        System.out.println(String.join(" ", blues));    // space
    }
}
