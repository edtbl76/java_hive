package com.basics.ControlStatements.For;

public class ForLoopExample {

    public static void main(String[] args) {

        /*
            For loops were designed to repeatedly iterate over a range of values until a particular condition
            is statisfied.

            INITIALIZATION: (this is the first statement in the loop).
                - this initializes the loop, and is executed once when the for loop begins

            TERMINATION EXPRESSION:
                - this is the condition at which the loop will end once the condition evaluates to false.

            INCREMENT EXPRESSION:
                - This determines how to handle the range of values for each iteration of the loop
                - increment? decrement? step?
         */
        for (int ctr = 1; ctr <= 5; ctr++)
            System.out.println(ctr);
    }
}
