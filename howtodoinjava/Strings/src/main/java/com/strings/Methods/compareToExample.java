package com.strings.Methods;

public class compareToExample {

    public static void main(String[] args) {

        /*
            int compareTo(String string)
                - compares 2 strings lexicographically based on the Unicode value of each char in the strings.
                - "Dictionary based comparison"

                - return value is 0, if argument string is equal to THIS string.
                - return value < 0, if THIS string is lexicographically less than string argument
                - return value > 0, if THIS string is lexicographically greater than string argument

            int compareToIgnoreCase(String string

                - same as above, but case insensitive
         */

        String thisString = "KravMaga";
        String comparator = "kravmaga";

        int sensitiveResult = thisString.compareTo(comparator);

        // case sensitive
        if (sensitiveResult > 0) {
            System.out.println(thisString + " is lexicographically greater than " + comparator);
        } else if (sensitiveResult < 0) {
            System.out.println(thisString + " is lexicographically less than " + comparator);
        } else {
            System.out.println(thisString + " and " + comparator + " are lexicographically equal.");
        }


        int insensitiveResult = thisString.compareToIgnoreCase(comparator);

        // case insensitive
        if (insensitiveResult > 0) {
            System.out.println(thisString + " is lexicographically greater than " + comparator);
        } else if (insensitiveResult < 0) {
            System.out.println(thisString + " is lexicographically less than " + comparator);
        } else {
            System.out.println(thisString + " and " + comparator + " are lexicographically equal.");
        }

    }
}
