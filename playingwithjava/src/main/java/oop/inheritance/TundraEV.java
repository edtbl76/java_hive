package oop.inheritance;

// Direct/Multi-level inheritance.
public class TundraEV extends Truck {
    public TundraEV( String color, int year, String bodyStyle) {
        super("Toyota", color, year, "Tundra", bodyStyle);
    }

    @SuppressWarnings("java:S106")
    public void chargeTruck() {
        System.out.println("Charging!");
    }
}
