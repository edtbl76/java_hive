package EnumCollections;

import EnumInheritance.Direction;

import java.util.EnumSet;
import java.util.Set;

public class EnumSetExample {

    public static void main(String[] args) {
        Set enumSet = EnumSet.of(Direction.EAST, Direction.WEST, Direction.NORTH, Direction.SOUTH);
    }
}
