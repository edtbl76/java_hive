package Basic;

import java.util.List;

public class EnumOrdinalExample {

    public static void main(String[] args) {
        List<Direction> directions = List.of(
                Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST
        );

        directions.forEach(
                value -> System.out.println(value + ": " + value.ordinal())
        );
    }
}
