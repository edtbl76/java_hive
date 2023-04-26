@SuppressWarnings("ALL")
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        // Calling the parent!
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    // This is the added functionality!
    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Border Color: Red");
    }
}
