package com.emangini.java9.InterfacePrivateMethods;

/*
    Java7 (and Before)
    - Interfaces were super duper simple.
    - They could ONLY contain public abstract methods.
    - the interface methods MUST MUST MUST be implemented by classes which choose to implement the interface.
 */
public interface Java7InterfaceExample {
    public abstract void method();
}

class CustomClass implements Java7InterfaceExample {
    @Override
    public void method() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        Java7InterfaceExample instance = new CustomClass();
        instance.method();
    }
}
