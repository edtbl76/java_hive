package com.strings.Methods;

public class substringExample {

    public static void main(String[] args) {

        /*
            String substring(int beginindex)
                - returns substring of the string starting w/ the character at the specified index.

            String substring(int beginIndex, int endIndex)
                - returns substring of the string starting at beginIndex and  ending just before character at endindex
         */

        String funword = "Forest";

        // start only
        System.out.println(funword.substring(2));

        // Start and end
        System.out.println(funword.substring(1, 4));
    }
}
