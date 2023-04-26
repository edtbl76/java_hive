package com.basics.PairsAndTuples;

import org.javatuples.Quartet;

public class JavaTupleIterationExample {

    public static void main(String[] args) {
        Quartet<String, String, String, String> satch = Quartet.with(
                "Joe Satriani",
                "Stu Hamm",
                "Jeff Campitelli",
                "Jonathan Mover"
        );

        for (Object obj : satch) {
            System.out.println(obj);
        }
    }
}
