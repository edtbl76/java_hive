package com.basics.ControlStatements.While;

public class WhileWithBreak {

    public static void main(String[] args) {

        /*
            This is a fairly contrived example, but it is a fair demonstration of how a break statement can be
            used to end a while loop as long as that break statement meets some condition.

            This is almost the exact opposite of the purpose of an IF-ELSE statement.
            - rather than executing code based on a condition happening, we are creating a loop where code
            constantly executes until a condition occurs.
         */
        int i = 1;
        while (true) {
            if (i <= 5) {
                System.out.println(i);
                i++;
            } else {
                break;  // exists loop.
            }
        }
    }
}
