package com.basics.ControlStatements.While;

public class ConvertingForIntoWhile {

    public static void main(String[] args) {

        // The race of the century! For vs While.
        /*
            in a for loop, the condition-expression is optional,
            in a while loop it isn't.
         */

        // For version
        for (int i = 0; i < 5; ++i) {
            System.out.println(i);
        }

        // while version
        int j = 0;
        while (j < 5) {
            System.out.println(j);
            j++;
        }

    }
}
