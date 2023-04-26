package com.basics.DataTypes.FloatingPointComparisons;

public class SimpleComparison_NOTRECOMMENDED {

    public static void main(String[] args) {

        // Method 1
        double f1 = .0;
        for (int i = 1; i  <=11; i++)
            f1 += .1;

        // Method 2
        double f2 = .1 * 11;

        System.out.println("f1: " + f1);
        System.out.println("f2: " + f2);

        /*
         using == isn't recommended because it can't handle the annoying rounding errors.
         */
        if (f1 == f2)
            System.out.println("f1 and f2 are equal");
        else
            System.out.println("f1 and f2 aren't equal");
    }
}
