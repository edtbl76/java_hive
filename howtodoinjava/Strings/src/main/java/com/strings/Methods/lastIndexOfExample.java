package com.strings.Methods;

public class lastIndexOfExample {

    public static void main(String[] args) {

        /*
            int lastIndexOf(int ch)
                - returns last occurrence of the char in the string

            int lastIndexOf(int ch, int fromIndex)
                - returns last occurrence of the char in the string, searching backwards starting at fromIndex.

            int lastIndexOf(String str)
                - returns last occurrence of the substring in the string

            int lastIndexOf(String str, int fromIndex)
                - returns last occurrence of the substring  in the string searching backwards starting at fromIndex

         */

        String oleMiss = "Mississippi";

        // Last Occurrence (Char)
        System.out.println(oleMiss.lastIndexOf('s'));

        // Next to last Occurrence (Char)
        System.out.println(oleMiss.lastIndexOf('s', oleMiss.lastIndexOf('s') - 1));

        // Last Occurrence (String)
        System.out.println(oleMiss.lastIndexOf("is"));

        // Next to last Occurrence (String)
        System.out.println(oleMiss.lastIndexOf("is", oleMiss.lastIndexOf("is") - 1));

    }
}
