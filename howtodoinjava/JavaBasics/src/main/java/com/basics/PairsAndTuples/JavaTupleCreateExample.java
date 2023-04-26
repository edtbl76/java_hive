package com.basics.PairsAndTuples;

import org.javatuples.Pair;
import org.javatuples.Quartet;

import java.util.Arrays;
import java.util.List;

public class JavaTupleCreateExample {

    public static void main(String[] args) {


        // with() Factory Method
        Pair<Integer, String> pair = Pair.with(1994, "WA");

        // Constructor creation
        Pair<Integer, String> grad = new Pair<>(1994, "WA");

        // Quartet Creation
        List<String> family = Arrays.asList("Dadphael", "Mommatello", "Connardo", "Bubbalangelo");
        Quartet<String, String, String, String> turtles = Quartet.fromCollection(family);

        // Create pairs from quarter
        Pair<String, String> youngOnes = Pair.fromIterable(family, 2);

        System.out.println(pair);
        System.out.println(grad);
        System.out.println(turtles);
        System.out.println(youngOnes);

    }
}
