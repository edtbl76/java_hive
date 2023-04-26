package com.oop.Constructors;

public class DfltConstructor2 {

    public static void main(String[] args) {

        /*
            NOTE: I didn't  specify  the Default  Constructor or the toString() override, and I still
            got the same result.

            Java is smaht and does some things for you!
         */
        DfltConstructor2 dc2 = new DfltConstructor2();
        System.out.println(dc2);
    }
}
