package StructuralDesignPatterns.Bridge.ShapeWithBridge_2;

public abstract class Shape {

    /*
        Uses object composition.
        - Color attribute is another object.
        - the constructor also takes the Object (composition).
     */
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    /*
        The abstract method allows the classes that extend this class to
        leverage object composition in order to call applyColor() against the
        object's color.
     */
    abstract public void applyColor();
}
