package StructuralDesignPatterns.Bridge.ShapeWOBridge_1;

public class BlueCircle extends Circle {
    @Override
    public void applyColor() {
        System.out.println("Applying color blue");
    }
}
