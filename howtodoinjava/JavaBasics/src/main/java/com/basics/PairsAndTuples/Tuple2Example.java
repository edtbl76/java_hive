package com.basics.PairsAndTuples;

import io.vavr.Tuple2;

public class Tuple2Example {

    public static void main(String[] args) {
        Tuple2<Integer, String> pair = new Tuple2<Integer, String>(32, "Chris Carson");

        Integer key = pair._1();
        String value = pair._2();

        System.out.println(pair.equals(new Tuple2<>(key, value)));  // true - same name and value
        System.out.println(pair.equals(new Tuple2<>(24, value)));  // false - different key (first element)
        System.out.println(pair.equals(new Tuple2<>(key, "Marshawn Lynch" )));  // false - different value (second element)
    }
}
