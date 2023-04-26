package StructuralDesignPatterns.Bridge.ShapeWOBridge_1;

public class RedSquare extends Square {
    @Override
    public void applyColor() {
        System.out.println("Applying color red");
    }
}
