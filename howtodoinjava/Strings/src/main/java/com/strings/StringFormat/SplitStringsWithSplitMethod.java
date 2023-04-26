package com.strings.StringFormat;

import java.util.Scanner;

public class SplitStringsWithSplitMethod {

    /*
        It is recommended to use String.split() instead of StringTokenizer
        - Instead of whatever arcane magic StringTokenizer is using under the hood,
        - String.split() simply returns tokens as a String array, which is much more flexible.

        NOTE:
        - the array doesn't drain after you iterate, so the tokens remain. This allows you MUCH more flexibility.
        (You can certainly drain it if you want, but you aren't FORCED into it)

        - the  delimiter is a regex.

     */
    public static void main(String[] args) {

        String[] tokens = getInput().split(",");    // Comma Delimited.
        System.out.println("No of tokens: " + tokens.length);
        for (String t : tokens) {
            System.out.println(t);
        }
        System.out.println("No of tokens: " + tokens.length);
    }

    private static String getInput() {
        System.out.println("Please enter a string: ");
        return new Scanner(System.in).nextLine();
    }
}
