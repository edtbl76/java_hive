package com.emangini.java9.InterfacePrivateMethods;

public interface Java8InterfaceExample {

    public abstract void method1();

    public default void method2() {
        System.out.println("Default method!");
    }

    public static void method3() {
        System.out.println("Static method");
    }
}

class CustomClassJava8 implements Java8InterfaceExample {

    @Override
    public void method1() {
        System.out.println("abstract method");
    }

    public static void main(String[] args) {
        Java8InterfaceExample instance = new CustomClassJava8();

        // Abstract Interface MUST be overridden within the class implementing the interface.
        instance.method1();

        // Default Interface does NOT have to be overriden, because a default value is provided
        // inside the interface definition.
        instance.method2();

        // Static Method doesn't have to be overridden.. and since it is static, I can access it directly
        // through a static reference to the Interface.
        Java8InterfaceExample.method3();
    }
}
