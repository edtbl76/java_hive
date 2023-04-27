package oop.abstraction.abstractclasses;

// abstract class
@SuppressWarnings("java:S1610")
public abstract class Animal {

    // abstract method
    @SuppressWarnings("unused")
    public abstract void makeSound();

    @SuppressWarnings("java:S106")
    public void move() {
        System.out.println(getClass().getSimpleName() + " is moving");
    }
}
