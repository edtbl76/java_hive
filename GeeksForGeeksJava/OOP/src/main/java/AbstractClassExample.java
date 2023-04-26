abstract class Shape {

    // data member
    private final String color;

    // Abstract method(s)
    @SuppressWarnings("unused")
    abstract double area();
    public abstract String toString();

    public Shape(String color) {
        this.color = color;
        System.out.println("Shape constructor called");
    }

    // Concrete Method
    public String getColor() {
        return color;
    }
}

class Circle extends Shape {
    private final double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
        System.out.println("Circle constructor called");
    }


    @Override
    double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "color=" + super.getColor() +
                ", area=" + area() +
                '}';
    }
}

class Rectangle extends Shape {

    private final double length;
    private final double width;

    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
        System.out.println("Rectangle constructor called");
    }

    @Override
    double area() {
        return length * width;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "color=" + super.getColor() +
                ", area=" + area() +
                '}';
    }
}


public class AbstractClassExample {

    public static void main(String[] args) {

        Shape shape1 = new Circle("Green", 2.2);
        Shape shape2 = new Rectangle("Blue", 2, 4);

        System.out.println(shape1);
        System.out.println(shape2);

    }
}
