package com.basics.Recursion;

import java.util.Scanner;

public class Fibonacci {

    public static int fibonacci(int n) {
        if (n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci( n - 2);
    }

    public static void main(String[] args) {

        System.out.println("Please enter a number: ");
        String input = new Scanner(System.in).nextLine();
        for( int i = 1; i <= Integer.parseInt(input); i++)
            System.out.println(fibonacci(i));
    }

}
