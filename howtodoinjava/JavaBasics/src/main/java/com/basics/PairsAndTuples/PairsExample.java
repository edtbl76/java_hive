package com.basics.PairsAndTuples;

import org.apache.commons.lang3.tuple.ImmutablePair;
public class PairsExample {

    public static void main(String[] args) {
        ImmutablePair<Integer, String> pair = ImmutablePair.of(3, "Russell Wilson");

        Integer key = pair.getKey();
        String value = pair.getValue();

        Integer left = pair.getLeft();
        String right = pair.getRight();

        System.out.println(pair.equals(ImmutablePair.of(key, value)));  // true - same name and value
        System.out.println(pair.equals(ImmutablePair.of(left, "Matt Hasselbeck")));  // true - same name and value
        System.out.println(pair.equals(ImmutablePair.of(8, right)));  // true - same name and value


    }
}
