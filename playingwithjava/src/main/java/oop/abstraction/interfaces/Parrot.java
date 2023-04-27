package oop.abstraction.interfaces;

public class Parrot extends Bird implements CanFly {
    @SuppressWarnings("java:S106")
    @Override
    public void flying() {
        System.out.println("Parrot is flying!");
    }
}
