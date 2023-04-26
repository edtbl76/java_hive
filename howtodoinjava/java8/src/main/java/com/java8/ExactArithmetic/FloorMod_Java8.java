package com.java8.ExactArithmetic;

import java.util.Arrays;
import java.util.List;

public class FloorMod_Java8 {

    /*
        Look at FloorMod old first. You will see how this result is accurate.
     */
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 11, -15, -16);

        for (Integer i : list) {
            System.out.println(i + " mod 2 = " + Math.floorMod(i, 2));
        }
    }
}
