package oop.abstraction.abstractclasses;

public class Dog extends Animal {
    @SuppressWarnings("java:S106")
    @Override
    public void makeSound() {
        System.out.println("Woof woof");
    }
}
