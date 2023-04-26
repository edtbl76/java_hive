package com.javaoop.Static;

import static java.lang.System.out;

public class DataObject {

    public static Integer staticVar;    // static variable
    public Integer nonStaticVar;

    // This is executed first
    static {
        staticVar = 40;
    }

    // Executed Second
    static {
        out.println("Hello! " + staticVar);
    }

    /*
        Inner Class can be static.
            - just like other static members, nested classes belong w/ CLASS SCOPE
            - so inner class can be accessed without having an object of outer class.

            Static Inner classes  can't access the NON-STATIC members of OUTER CLASS.
            - it can only access static members from outer class
     */
    static class StaticInnerClass {
        Integer innerNonStaticVar = 60;
        static Integer innerStaticVar = 70;

        public static void accessOuterClass() {
            System.out.println(DataObject.staticVar);       // static var of outer class
            System.out.println(DataObject.getStaticVar());  // static meth of outer class.
        }
    }

    public static Integer getStaticVar() {
        return staticVar;
    }

}
