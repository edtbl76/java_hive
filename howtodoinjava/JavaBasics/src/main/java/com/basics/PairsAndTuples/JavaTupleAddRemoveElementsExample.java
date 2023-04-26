package com.basics.PairsAndTuples;

import com.basics.ControlStatements.IfElse.OptionalElseBlock;
import org.javatuples.Pair;
import org.javatuples.Quartet;
import org.javatuples.Septet;
import org.javatuples.Triplet;

import java.util.Arrays;
import java.util.List;

public class JavaTupleAddRemoveElementsExample {

    public static void main(String[] args) {

        // Start w/ a Pair
        Pair<Integer, String> pair = Pair.with(978, "Middlesex County");
        System.out.println(pair);

        // Triplet
        Triplet<Integer, String, String> triplet = pair.add("Section A"); // made up
        System.out.println(triplet);

        // Start w/ a Triplet and Quartet
        Triplet<String, String, String> triplet1 = Triplet.with("Java", "C++", "Python");

        // addAt() Method
        // (Allows you to add an element at whatever position you want)
        Quartet<String, String, String, String> quartet = triplet1.addAt1("Go");

        // adding Tuples of sizeX to sizeY = size(X + Y)
        Septet septet = quartet.add(triplet1);
        System.out.println(triplet1);
        System.out.println(quartet);
        System.out.println(septet);

        /*
            Convert Tuple to Collection/Array
         */
        List<Object> quartetList = quartet.toList();
        System.out.println("As List: " + quartetList);

        Object[] quartetArray = quartet.toArray();
        System.out.println("As Array: " + Arrays.toString(quartetArray));


    }
}
