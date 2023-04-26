package com.basics.ControlStatements.While;

public class WhileLoopExample {
    public static void main(String[] args) {

        // While statement/loop will continually execute a block of statements as long as a particular
        // condition remains true.
        // It just keeps on going until the expression evaluates to false.
        // Yes... this means it is possible to create a loop that won't ever end.

        int count = 1;
        while (count < 5) {
            System.out.println("Count is: " + count);
            count++;
        }
    }
}
