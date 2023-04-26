public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        //get a circle and call its method
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        shape1.draw();

        //get a rectangle and call its method
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        //get a square and call its method
        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();

    }
}
