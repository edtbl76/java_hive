package com.java8.FunctionalInterface;

@FunctionalInterface
public interface FunctionalInterfaceExample {
    public void firstWork();

    default void moreWork1() {
        // I'm a default method, and therefore not abstract.
    }

    default void moreWork2() {
        // I'm another default method, and still not abstract.
        // There can be MANY of me :)
    }

    @Override
    public String toString();       // Overriden from java.lang.Object, doesn't count as abstract method

    @Override
    public boolean equals(Object obj);  // Overriden from java.lang.Object. Doesn't count as abstract method.
}
