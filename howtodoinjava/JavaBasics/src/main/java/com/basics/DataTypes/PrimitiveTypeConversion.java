package com.basics.DataTypes;

public class PrimitiveTypeConversion {

    public static void main(String[] args) {

        int counter = 20_000_000;

        // Assign int to short (NARROWING)
        /*
            REMEMBER: this requires an explicit type-cast. If you forget, you'll get a TYPE-MISMATCH ERROR
         */
        short shortCounter = (short) counter;

        // Assign int to long (WIDENING)
        long longCounter = counter;

        System.out.println(counter);
        System.out.println(shortCounter);
        System.out.println(longCounter);
    }
}
