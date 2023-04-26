package com.java7.changes;

import java.util.Arrays;
import java.util.List;

public class SupportForBinaryLiterals {

    public static void main(String[] args) {

        List<Integer> binaries = Arrays.asList(
                0b1,
                0b10,
                0b11,
                0b100,
                0b101,
                0b110,
                0b111,
                0b1000,
                0b1001,
                0b1010
        );

        for (Integer bin : binaries) {
            System.out.println(bin.toString());
        }
    }
}
