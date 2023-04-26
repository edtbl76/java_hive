package com.javaoop.Basics;

public class StaticInitializerExample {

    /*
        NOTE:
            - it is considered bad form to do this.
            I put this backwards from the order it will actually execute.

            I  DID:
            - Constructor
            - Instance
            - Static

            It should execute
            - Static
            - Instance
            - Constructor
     */
    private StaticInitializerExample() {
        System.out.println("Inside Constructor");
    }

    {
        System.out.println("Inside Instance Initializer");
    }

    static {
        System.out.println("Inside Static Initializer");
    }


    public static void main(String[] args) {
        new StaticInitializerExample();
    }
}
