package StructuralDesignPatterns.Bridge.ShapeWOBridge_1;

public class BlueSquare extends Square {
    @Override
    public void applyColor() {
        System.out.println("Applying color blue");
    }
}
