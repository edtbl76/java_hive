package oop.abstraction.abstractclasses;

public class Cat extends Animal {

    @SuppressWarnings("java:S106")
    @Override
    public void makeSound() {
        System.out.println("Meow meow");
    }
}
