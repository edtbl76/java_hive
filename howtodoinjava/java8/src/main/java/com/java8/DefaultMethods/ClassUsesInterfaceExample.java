package com.java8.DefaultMethods;

public class ClassUsesInterfaceExample implements ExampleInterface {

    public void doSomething() {
        System.out.println("I'm overriding the ExampleInterface I'm implementing.");
    }
    public static void main(String[] args) {
        ClassUsesInterfaceExample clazz = new ClassUsesInterfaceExample();
        clazz.doSomething();
    }
}
