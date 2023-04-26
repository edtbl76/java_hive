package com.basics.ControlStatements.ForEach;

public class ForEachExample {

    public static void main(String[] args) {

        /*
            For-Each is a cleaner syntax, and it is easier to read.
            It's also known as a Range Based Loop or an Enhanced For Loop.

            It's specific use case is for iterating over arrays and collections
         */

        int[] numList = { 10, 20, 30, 40, 50};

        for(int num: numList) {
            System.out.println(num);
        }
    }
}
