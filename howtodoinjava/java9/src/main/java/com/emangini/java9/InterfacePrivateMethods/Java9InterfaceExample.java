package com.emangini.java9.InterfacePrivateMethods;

/**
 * Java 9 adds two types of methods in interfaces.
 * - Private Methods
 * - Private Static Methods
 *
 * - allows 2 default methods that need to share code to do so via the private interface method,
 * without exposing that private method to the implementing classes.
 *
 * - FOUR RULES
 * 1.) Private interface method CANNOT BE ABSTRACT
 * 2.) Private method can ONLY be used inside the interface
 * 3.) Private Static Method can be used inside other static and non-static interface methods
 * 4.) Private non-static methods cannot be used inside private static methods/
 *
 */


public interface Java9InterfaceExample {

    // good old-fashioned abstract method from Java7
    public abstract void method1();

    // public default method coming from Java 8
    public default void method2() {

        // PRIVATE method inside default method
        method4();

        // static method inside other non-static method
        method5();
        System.out.println("default method");
    }

    // public static method coming from Java 8
    public static void method3() {

        // static method inside other static method
        method5();
        System.out.println("static method");
    }

    // private method JAVA 9
    private void method4() {
        System.out.println("private non-static method");
    }

    // private static method Java9
    private static void method5() {
        System.out.println("private static method");
    }
}

class CustomClassJava9 implements Java9InterfaceExample {

    // override the public abstract method
    @Override
    public void method1() {
        System.out.println("Abstract Method, overriden within implementing class!");
    }

    public static void main(String[] args) {
        Java9InterfaceExample instance = new CustomClassJava9();

        // This will call the method1() (overrided) from within this class.
        instance.method1();

        // This COULD call an overrided version, but since none is available, it calls the default method2()
        // from within the interface.
        // This also calls the 2 private methods method4() and method5()
        instance.method2();

        // This uses a "class"(interface) reference to access the static method3.
        // This can only access STATIC members, so it only calls method5() because method4() is non-static
        Java9InterfaceExample.method3();
    }
}
