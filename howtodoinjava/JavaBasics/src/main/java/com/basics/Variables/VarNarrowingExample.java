package com.basics.Variables;

public class VarNarrowingExample {

    public static void main(String[] args) {

        // Narrowing from int to byte
        int i = 198;
        byte j = (byte) i;

        System.out.println(i);
        System.out.println(j);  // data gets lost!

        /*
            Expected result is:

                198
                -58  (due to lost data)
         */
    }
}
