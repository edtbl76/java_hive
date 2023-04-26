package com.basics.PairsAndTuples;

import org.javatuples.Pair;

public class JavaTupleGetValuesExample {

    public static void main(String[] args) {

        // Create a Pair For This Demonstration
        Pair<Integer, String> pair = Pair.with(141, "Glucose");

        // getValue() methods
        // getValueX(), where X is the element position in the tuple
        System.out.println("Measurement: " + pair.getValue0());
        System.out.println("Test       : " + pair.getValue1());

        // getValue(int index)
        //  NOTE: this is NOT type safe.
        System.out.println("Measurement: " + pair.getValue(0));
        System.out.println("Test       : " + pair.getValue(1));

    }
}
