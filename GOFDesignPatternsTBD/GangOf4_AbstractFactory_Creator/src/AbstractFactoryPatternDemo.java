import java.util.Objects;

public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {

        // get shape factory, get its shapes, and then call their methods
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

        // (This is a best practices solution). I'm preventing a nullable
        Shape shape1 = Objects.requireNonNull(shapeFactory).getShape("CIRCLE");
        shape1.draw();

        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        shape2.draw();

        Shape shape3 = shapeFactory.getShape("SQUARE");
        shape3.draw();

        // Rinse and repeat w/ the colors
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        // (Best practices solution... is to pass the object to Objects.requireNonNull)
        Color color1 = Objects.requireNonNull(colorFactory).getColor("RED");
        color1.fill();

        Color color2 = colorFactory.getColor("GREEN");
        color2.fill();

        Color color3 = colorFactory.getColor("BLUE");
        color3.fill();

    }
}
