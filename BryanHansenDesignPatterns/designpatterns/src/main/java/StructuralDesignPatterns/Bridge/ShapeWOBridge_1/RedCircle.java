package StructuralDesignPatterns.Bridge.ShapeWOBridge_1;

public class RedCircle extends Circle{
    @Override
    public void applyColor() {
        System.out.println("Applying color red");
    }
}
