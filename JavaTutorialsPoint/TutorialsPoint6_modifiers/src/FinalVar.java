@SuppressWarnings("unused")
public class FinalVar {
    final int value = 10;

    // These are constants:
    private static final int WIDTH = 6;
    private static final String TITLE = "Title";

    public static void main(String[] args) {
        System.out.println("The width of " + TITLE + " is " + WIDTH);
    }
}
