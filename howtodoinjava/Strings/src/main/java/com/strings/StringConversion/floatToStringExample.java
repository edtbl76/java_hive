package com.strings.StringConversion;

import javax.swing.text.NumberFormatter;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class floatToStringExample {

    public static void main(String[] args) {
        float pi = 3.1415927F;

        // METHOD 1: Float.toString
        System.out.println(Float.toString(pi));

        // METHOD 2: String.valueOf(Float f)
        System.out.println(String.valueOf(pi));

        // Method 3: NumberFormatter
        /*
            This presets the precision, so that any future numbers will use this format.
            (1.) you have to remember to pass the output through the formatter.
            (2.) passing non floats through will get you extra digits (as the last example shows)
         */
        NumberFormat formatter = new DecimalFormat("0.00");
        System.out.println(formatter.format(pi));
        System.out.println(formatter.format(1));
    }
}
