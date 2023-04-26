import java.util.stream.IntStream;

public class FlyweightPatternDemo {

    private static final String[] COLORS = {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {

        // This is instead of a for loop in the classic C sense
        // New Java 8 version
        IntStream.range(0, 20).forEach(
                n -> {
                    Circle circle = (Circle)ShapeFactory.getCircle(getRandomColor());
                    circle.setX(getRandomX());
                    circle.setY(getRandomY());
                    circle.setRadius(100);
                    circle.draw();
                }
        );


    }

    private static String getRandomColor() {
        return COLORS[(int)(Math.random()*COLORS.length)];
    }

    private static int getRandomX() {
        return (int)(Math.random()*100);
    }

    private static int getRandomY() {
        return (int)(Math.random()*100);
    }
}
