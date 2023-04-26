package StructuralDesignPatterns.Bridge.ShapeWithBridge_2;

public class Green implements Color {

    @Override
    public void applyColor() {
        System.out.println("Applying color green");
    }
}
