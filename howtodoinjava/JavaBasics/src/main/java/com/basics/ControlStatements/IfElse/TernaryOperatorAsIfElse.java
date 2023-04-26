package com.basics.ControlStatements.IfElse;

public class TernaryOperatorAsIfElse {

    public static void main(String[] args) {

        int num1 = 0, num2 = 0, result;

        // if-else example
        /*
            NOTE: if there is only a single statement, you can omit the curly braces. I personally DONT like to do this.
         */
        if (num1 < num2)
            result = num1;
        else
            result = num2;

        // ternary example
        result = (num1 < num2) ? num1 : num2;

        //BETTER?
        result = Math.min(num1, num2);
    }
}
