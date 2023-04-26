package com.java7.changes;

import java.util.Scanner;

public class SwitchStatementSupportsStringClass {

    public static void main(String[] args) {
        String token = getToken();

        switch(token) {
            case "one":
                System.out.println("Token " + token + " identified");
                break;
            case "two":
                System.out.println("Token " + token + " identified");
                break;
            default:
                System.out.println("This example sucks.");
                break;
        }
    }

    private static String getToken() {
        System.out.println("Please enter a string: ");
        return new Scanner(System.in).nextLine();
    }
}
