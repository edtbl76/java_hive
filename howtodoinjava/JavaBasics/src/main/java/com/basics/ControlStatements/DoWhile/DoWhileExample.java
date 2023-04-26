package com.basics.ControlStatements.DoWhile;

public class DoWhileExample {

    public static void main(String[] args) {

        /*
            The difference between the while and do-while loop is that the do-while loop evalutes its
            expression at the bottom of the loop instead of the top.

            The purpose for this is a use case where the "do block" is always executed at least once.
         */

        int i = 1;
        int sum = 0;

        do {
            sum += i;
            i++;
        } while (i <= 10);

        System.out.println(sum);
    }
}
