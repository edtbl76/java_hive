package com.strings.StringFormat;

import java.util.Scanner;

public class ReverseString_wo_Recursion {

    public static void main(String[] args) {

        System.out.println("Please Enter A String: ");

        // I like chaining, so this might be ugly to others. I just want to demonstrate the power of OOP when calls to
        // classes like that return *this for chaining.
        // At any rate, while i'm babbling.. StringBuilder has a method for  reverse.
        System.out.println(
                new StringBuilder(
                        new Scanner(System.in)
                                .nextLine()).reverse());
    }
}
