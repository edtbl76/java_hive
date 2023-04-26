package com.javaoop.Static;

public class StaticVariableExample {

    /*
        STATIC VARIABLE SYNTAX

            ACCESS_MODIFIFER static DATA_TYPE VARNAME;

        - static variables belong to the CLASS LEVEL (not an instance of the class)
        - ONLY ONE COPY OF VARIABLE IN RUNTIME (it's a Singleton)
        - every instance of a class has access to that single copy.
            - Separate instances don't have their own local copy as they would for non-static vars.

     */

    public static void main(String[] args) {
        // static variables can be accessed directly through the class. We don't have to access them via an instance of the class.
        DataObject.staticVar = 10;

        DataObject do1 = new DataObject();
        do1.nonStaticVar = 20;

        System.out.println("DataObject 1 Static    : " + do1.staticVar);    // I usually just perform direct access.
        System.out.println("DataObject 1 Non-Static: " + do1.nonStaticVar);

        DataObject do2 = new DataObject();

        System.out.println("DataObject 2 Static    : " + do2.staticVar);
        System.out.println("DataObject 2 Non-Static: " + do2.nonStaticVar);

        /*
            NOTE: usually I access static members directly from the class and avoid accessing them via the instance.
            The examples above were just to demonstrate the concept of static class scope.
         */
    }
}

