package com.java8.ExactArithmetic;

import java.util.Arrays;
import java.util.List;

public class FloorMod_old {

    public static void main(String[] args) {

        /*
            The result here  is going to show a  -1 for -15, which isn't possible.

                Basic Math:
                    - n mod 2 =
                        0 for n = even

                        1 for n = odd
         */
        List<Integer> list = Arrays.asList(10, 11, -15, -16);
        for (Integer i : list) {
            System.out.println(i +  " mod 2 = " + i % 2);
        }
    }
}
