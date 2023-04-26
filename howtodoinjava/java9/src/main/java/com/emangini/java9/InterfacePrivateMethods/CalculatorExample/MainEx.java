package com.emangini.java9.InterfacePrivateMethods.CalculatorExample;

public class MainEx implements CustomCalculator {

    public static void main(String[] args) {
        CustomCalculator demo = new MainEx();

        int evenSum = demo.addEvenNumbers(1,2,3,4,5,6,7,8,9);
        int oddSum = demo.addOddNumbers(1,2,3,4,5,6,7,8,9);

        System.out.println(evenSum);
        System.out.println(oddSum);
    }


}
