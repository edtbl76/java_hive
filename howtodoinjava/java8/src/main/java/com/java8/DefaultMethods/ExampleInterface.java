package com.java8.DefaultMethods;

public interface ExampleInterface {

    default void doSomething() {
        System.out.println("Something");
    }
}
