package com.java8.ExactArithmetic;

public class ExactMethods {

    private static int number1 = 5;
    private static int number2 = 2;

    public static void main(String[] args) throws ArithmeticException {

        System.out.println("Addition: ");
        System.out.println(number1 + number2);
        System.out.println(Math.addExact(number1, number2));

        System.out.println("\nSubtraction: ");
        System.out.println(number1 - number2);
        System.out.println(Math.subtractExact(number1, number2));

        System.out.println("\nMultiply: ");
        System.out.println(number1 * number2);
        System.out.println(Math.multiplyExact(number1, number2));

        System.out.println("\nIncrement: ");
        System.out.println(++number2);  // pre increment so we return the result immediately.
                                        // post increment stores the original result temporarily
        System.out.println((Math.incrementExact(number2)));

        System.out.println("\nDecrement: ");
        System.out.println(--number1);  // pre decrement so we return the result immediately
                                        // post decrement stores the original result temporarily
        System.out.println(Math.decrementExact(number1));

        System.out.println("\nNegate: ");
        System.out.println(-number1);
        System.out.println(Math.negateExact(number1));

        // Handling the exception
        try {
            System.out.println(Math.addExact(Integer.MAX_VALUE, Integer.MAX_VALUE));
        } catch (ArithmeticException ae) {
            System.out.println(ae.toString());
        }
    }
}
