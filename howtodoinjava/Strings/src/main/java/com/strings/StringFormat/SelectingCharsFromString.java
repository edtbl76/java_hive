package com.strings.StringFormat;

import java.util.Scanner;

public class SelectingCharsFromString {

    public static void main(String[] args) {
        String str = getInput();

        int count = 5;
        //
        System.out.println("Original: " + str);
        System.out.println("First " + count + " Chars:" + getFirstChars(str, count));
        System.out.println("Last " + count + " Chars:" + getLastChars(str, count));
    }

    private static String getInput() {
        System.out.println("Enter a string: ");
        return new Scanner(System.in).nextLine();
    }

    private static String getFirstChars(String str, int count) {

        return (str.length() > count) ? str.substring(0, count) : str;
    }

    private static String getLastChars(String str, int count) {

        return (str.length() > count) ? str.substring(str.length() - count) : str;
    }
}
