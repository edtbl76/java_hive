package StructuralDesignPatterns.Bridge.ShapeWithBridge_2;

public class Shape2BridgeDemo {

    /*
        We were able to implement GREEN without having to change any of the shapes.
        - this allows implementation and abstraction to vary independently


     */
    public static void main(String[] args) {

        Color blue = new Blue();
        Color red = new Red();
        Color green = new Green();

        Shape circle = new Circle(red);
        Shape greenCircle = new Circle(green);
        Shape square = new Square(blue);

        circle.applyColor();
        greenCircle.applyColor();;
        square.applyColor();
    }
}
