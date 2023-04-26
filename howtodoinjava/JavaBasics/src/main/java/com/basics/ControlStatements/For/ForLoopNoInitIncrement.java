package com.basics.ControlStatements.For;

public class ForLoopNoInitIncrement {

    public static void main(String[] args) {

        // Initialization and increment are optional parts of the for loop
        int num = 1; // initialization outside of the for loop
        for (; num <= 6 ;) {
            System.out.println(num);
            num++;  // handling incrementing inside the for loop.
        }
    }
}
