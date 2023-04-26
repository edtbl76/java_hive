package com.javaoop.Basics;


public class InstanceInitializerExample {

    // instance initializer
    {
        System.out.println("Inside instance initializer");
    }

    // Constructor
    public InstanceInitializerExample() {
        System.out.println("Inside the constructor");
    }

    public static void main(String[] args) {
        new InstanceInitializerExample();
    }
}
