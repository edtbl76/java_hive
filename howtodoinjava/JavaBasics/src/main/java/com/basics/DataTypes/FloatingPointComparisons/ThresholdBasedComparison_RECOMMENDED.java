package com.basics.DataTypes.FloatingPointComparisons;

public class ThresholdBasedComparison_RECOMMENDED {

    final static double THRESHOLD = .0001;

    public static void main(String[] args) {

        //  METHOD 1
        double f1 = .0;
        for (int i = 1; i <= 11; i++)
            f1 += .1;

        // METHOD 2
        double f2 = .1 * 11;

        System.out.println("f1 = " + f1);
        System.out.println("f2 = " + f2);

        if (Math.abs(f1 - f2) < THRESHOLD)
            System.out.println("f1 and f2 are equal within " + THRESHOLD);
        else
            System.out.println("f1 and f2 are unequal, because their difference exceeds the threshold: " + THRESHOLD);
    }
}
