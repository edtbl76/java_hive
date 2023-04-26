package Methods;

import java.util.Arrays;
import java.util.List;

public class NonAbstractEnumMethods {
    public static void main(String[] args) {
        List<Direction> list = Arrays.asList(Direction.values());
        list.forEach(Direction::printDirection);
    }
}

enum Direction {
    EAST,WEST,NORTH,SOUTH;

    protected void printDirection() {
        System.out.println("You are moving in " + this + " direction");
    }
}
