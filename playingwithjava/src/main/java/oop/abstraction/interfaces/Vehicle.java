package oop.abstraction.interfaces;


@SuppressWarnings("java:S106")
public interface Vehicle {

    void cleanVehicle();

    /*
        Similar to default methods, static methods can have a body,
        but they can't be overridden, protecting you from an
        accidental case of the diamond problem.
     */
    static void honkHorn() {
        System.out.println("beep beep");
    }

    default void startVehicle() {
        System.out.println("starting");
    }
}
