package com.basics.PairsAndTuples;

import org.javatuples.Pair;

public class JavaTupleSetValuesExample {

    public static void main(String[] args) {

        // setup
        Pair<Integer, String> pair = Pair.with(0, "default");
        System.out.println(pair);

        // MODIFY
        Pair<Integer,String> newPair = pair.setAt0(10).setAt1("NotDefault");

        System.out.println("[" + newPair.getValue0() + "]: " + newPair.getValue1());


    }
}
