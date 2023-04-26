package com.oop.Constructors;

public class DfltConstructor {

    DfltConstructor() {

    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static void main(String[] args) {

        /*
            This is actually going to print out the result of the java.lang.Object.toString() method.
            The default constructor  (and this  Override) don't have to be specified at all.

            A default constructor takes no arguments and doesn't do anything. It's just a placeholder.
         */
        DfltConstructor dc = new DfltConstructor();
        System.out.println(dc);
    }
}
