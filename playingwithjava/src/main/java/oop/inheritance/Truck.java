package oop.inheritance;


// Direct Inheritance (First Level)
@SuppressWarnings({"java:S106", "FieldCanBeLocal"})
public class Truck extends Vehicle {

    private final String bodyStyle;
    private static final int FUEL_CAPACITY = 200;

    public Truck(String make, String color, int year, String model, String bodyStyle) {
        super(make, color, year, model);
        this.bodyStyle = bodyStyle;
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Body Style: " + bodyStyle);
    }

    public void openTailgate() {
        System.out.println("Tailgate is open");
    }

    public void displayFuelCapacity() {
        System.out.println("Fuel Capacity from the Vehicle class: " + Vehicle.FUEL_CAPACITY);
        System.out.println("Fuel Capacity from the Truck Class: " + Truck.FUEL_CAPACITY);
    }
}
