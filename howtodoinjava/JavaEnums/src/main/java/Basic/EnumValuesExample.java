package Basic;

import java.util.Arrays;
import java.util.List;

public class EnumValuesExample {
    public static void main(String[] args) {
        List<Direction> compass = Arrays.asList(Direction.values());
        compass.forEach(System.out::println);
    }
}
