package com.basics.DataTypes.FloatingPointComparisons;

import java.math.BigDecimal;

public class BigDecimalComparison_RECOMMENDED {
    /*
        java.math.BigDecimal

        - considered the "best" method for comparing Floats and Doubles.
        - you can specify the ROUNDING MODE and EXACT PRECISION that you want to use.
            - using EXACT PRECISION limit, rounding errors are mostly  solved.

        - this type is IMMUTABLE, so once it has been initialized, the number can NEVER BE CHANGED.
     */

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("2.00");
        BigDecimal b = new BigDecimal("2.0");

        // NEVER use equals(), because it will compare the scale. If the scale is different (even if the numbers are
        // mathematically equal, they will be false)
        System.out.println(a.equals(b));        // false
        System.out.println(a.compareTo(b) == 0);    // true


    }
}
