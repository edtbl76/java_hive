package StructuralDesignPatterns.Bridge.ShapeWOBridge_1;

public class Shape1BridgeDemo {

    /*
        ORTHOGONAL (i.e. statistically independent) PROBLEM:
        - for every Color, I have to add 2 more objects
        - if I have to add a new shape, then I have to add multiple classes for each color.

        I end up creating a lot more objects than is necessary.

        It doesn't really expand very well.
     */
    public static void main(String[] args) {

        Circle circle = new BlueCircle();
        Square square = new RedSquare();

        circle.applyColor();
        square.applyColor();
    }
}
