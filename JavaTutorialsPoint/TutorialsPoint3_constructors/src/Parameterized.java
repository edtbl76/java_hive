/*
This is an example of a class w/ a parameterized constructor
 */
public class Parameterized {
    private int num;

    private Parameterized(int number) {
        this.num = number;
    }

    public static void main(String[] args) {
        Parameterized p1 = new Parameterized(10);
        Parameterized p2 = new Parameterized(20);
        // This is bad code.... use getters and setters!!!!
        System.out.println("Numbers: " + p1.num + ", " + p2.num);
    }
}
