package com.basics.Recursion;

public class GreatestCommonDivisor {

    public static int calculateGCD(int p, int q) {
        if (q == 0)
            return p;
        else
            return calculateGCD(q, p % q);
    }

    public static void main(String[] args) {
        int number1 = 40;
        int number2 = 500;

        System.out.println(calculateGCD(number1, number2));
    }
}
