package EnumCollections;

import Intermediate.Direction;

import java.util.EnumMap;
import java.util.Map;

public class EnumMapExample {

    public static void main(String[] args) {
        Map enumMap = new EnumMap(Direction.class);

        enumMap.put(Direction.EAST, Direction.EAST.getAngle());
        enumMap.put(Direction.WEST, Direction.WEST.getAngle());
        enumMap.put(Direction.NORTH, Direction.NORTH.getAngle());
        enumMap.put(Direction.SOUTH, Direction.SOUTH.getAngle());
    }
}
