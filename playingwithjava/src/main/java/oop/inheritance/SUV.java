package oop.inheritance;

// Direct Inheritance from Vehicle
// However, this is a sibling of Truck,
// which demonstrates Hierarchical / Parallel inheritance
public class SUV extends Vehicle {

    // This is actually a bad practice.
    // We shouldn't shadow parent class fields.
    @SuppressWarnings("java:S2387")
    static final int FUEL_CAPACITY = 50;
    public SUV(String make, String color, int year, String model) {
        super(make, color, year, model);
    }

    @SuppressWarnings("java:S106")
    public void displayFuelCapacity() {
        System.out.println("Fuel Capacity from the Vehicle class: " + Vehicle.FUEL_CAPACITY);
        System.out.println("Fuel Capacity from the SUV Class: " + FUEL_CAPACITY);
    }
}
