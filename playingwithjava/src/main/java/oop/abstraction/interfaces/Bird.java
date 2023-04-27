package oop.abstraction.interfaces;

public class Bird {

    @SuppressWarnings("java:S106")
    public void eat() {
        System.out.println(getClass().getSimpleName() + " is eating!");
    }
}
