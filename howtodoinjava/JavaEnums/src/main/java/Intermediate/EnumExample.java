package Intermediate;

import java.util.Arrays;
import java.util.List;

public class EnumExample {

    public static void main(String[] args) {

        List<Direction> directions = Arrays.asList(Direction.values());
        directions.forEach(direction -> {
            System.out.println("Direction: " + direction);
            System.out.println("Angle1: " + direction.getAngle());
        });

        // just another way to get the angle.
        System.out.println(Direction.NORTH.getAngle());
    }
}
