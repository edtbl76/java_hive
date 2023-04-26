package com.basics.ControlStatements.Switch;

public class SwitchStatement {

    public static void main(String[] args) {

        /*
            Advantage of a switch statement is to have multiple execution paths. (it is much simpler than a
            series of if, else-if blocks)

         */

        String value = "B";

        switch (value) {

            case "A":
                System.out.println("Value is A");
                break;
            case "B":
                System.out.println("Value is B");
                break;
            default:
                System.out.println("Value is neither A nor B");
                break;
        }
    }
}
