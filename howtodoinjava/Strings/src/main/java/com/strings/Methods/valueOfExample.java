package com.strings.Methods;

public class valueOfExample {

    /*
        static String valueOf()
            - returns string representation of passed args such as int long float double char and char array

        NOTE: THIS INCLUDES THE SAME IMPLEMENTATIONS OF COPYVALUEOF.

        This is actually the newer/preferred method.
        1.) apparently the underlying code is better.
        2.) this supports ALL 8 primitives, char arrays and Object references.
     */
    public static void main(String[] args) {


        int one = 1;
        String oneStr = String.valueOf(one);
        System.out.println(oneStr);
        System.out.println("One: " + one);  // this is shorter...

        long oneLong = 1L;
        String oneLongStr = String.valueOf(oneLong);
        System.out.println(oneLongStr);
        System.out.println("OneLong: " + oneLongStr);   // Still shorter.

        float oneFloat = 1.F;
        System.out.println(String.valueOf(oneFloat));   // This is grey, because its unnecessary
        System.out.println("OneFloat" + oneFloat);      // this is shorter

        double oneDouble= 1.;
        System.out.println(String.valueOf(oneDouble));  // Grey = unnecessary
        System.out.println("OneDouble: " + oneDouble);  // shorter.




    }
}
