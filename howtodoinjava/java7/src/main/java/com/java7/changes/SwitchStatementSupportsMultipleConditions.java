package com.java7.changes;

import java.util.Scanner;

public class SwitchStatementSupportsMultipleConditions {

    public static void main(String[] args) {
        System.out.println(handleNumber(getNumber()));
    }

    private static String getNumber() {
        System.out.println("Please type out a number in word form");
        return new Scanner(System.in).nextLine();
    }

    private static String handleNumber(final String number) {
        String value = null;

        switch(number) {
            case ("one"):
            case("three"):
            case("five"):
                value = "Your number is odd and less than or equal to five";
                break;

            case("two"):
            case("four"):
                value = "Your number is even and less than five";
                break;

            default:
                value = "Your number is greater than five.";

        }
        return value;

    }
}
