import java.util.List;

public class Tree {

    private static final List<String> SEGMENTS =
            List.of("  *  ", " *** ", "* ** *", "******", " **** ", "  **  ", "*****", "  *  ");

    public static void main(String[] args) {

        // Plant a Tree
        TreeOne();
        TreeTwo();
        TreeThree();


    }

    private static void TreeOne() {
        System.out.println();
        System.out.println(SEGMENTS.get(0));
        System.out.println(SEGMENTS.get(1));
        System.out.println(SEGMENTS.get(1));
        System.out.println(SEGMENTS.get(0));
        System.out.println(SEGMENTS.get(0));
        System.out.println();
    }

    private static void TreeTwo() {
        System.out.println();
        System.out.printf(" %s %n", SEGMENTS.get(2));
        System.out.printf(" %s %n", SEGMENTS.get(3));
        System.out.printf(" %s %n", SEGMENTS.get(4));
        System.out.printf(" %s %n", SEGMENTS.get(5));
        System.out.printf(" %s %n", SEGMENTS.get(5));
        System.out.println("~~~~~~~~~~");
        System.out.println();
    }

    private static void TreeThree() {
        System.out.println();
        System.out.printf(" %s %n", SEGMENTS.get(6));
        System.out.printf(" %s %n", SEGMENTS.get(6));
        System.out.printf(" %s %n", SEGMENTS.get(6));
        System.out.printf(" %s %n", SEGMENTS.get(7));
        System.out.printf(" %s %n", SEGMENTS.get(7));
        System.out.println("#######");
        System.out.println();
    }
}
