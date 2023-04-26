package EnumInheritance;

public class EnumComparisonExample {

    public static void main(String[] args) {
        Direction east = Direction.EAST;
        Direction newEast = Direction.valueOf("EAST");

        System.out.println(east == newEast);
        System.out.println(east.equals(newEast));
    }
}
