package StructuralDesignPatterns.Bridge.ShapeWithBridge_2;

public class Blue implements Color {

    @Override
    public void applyColor() {
        System.out.println("Applying color blue");
    }
}
