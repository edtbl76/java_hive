package oop.abstraction.interfaces;

public class Car implements Vehicle {

    @SuppressWarnings("java:S106")
    @Override
    public void cleanVehicle() {
        System.out.println("Cleaning my car.");
    }
}
