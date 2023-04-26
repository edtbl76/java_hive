/*
This is an example of a constructor that has no arguments.
 */
public class NoArguments {
    private final int num;
    private NoArguments() { num = 100; }

    private int getNum() { return num; }

    public static void main(String[] args) {
        NoArguments nunya = new NoArguments();
        System.out.println(nunya.getNum());
    }

}
