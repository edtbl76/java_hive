public class ParamByValue {

    private static void swapFunct(int a, int b) {
        // values are only swapped internally.
        System.out.println("Before swapping(Inside), a = " + a + " b = " + b);

        int c = a;
        a = b;
        b = c;
        System.out.println("After swapping(Inside), a = " + a + " b = " + b);
    }

    public static void main(String[] args) {
        int a = 30;
        int b = 45;
        System.out.println("Before swapping, a = " + a + " b = " + b);

        swapFunct(a, b);
        System.out.println("\n** Now, Before and After swapping values will be same here**:");
        // values are the same as the way they started, because the function operated only on local scope.
        System.out.println("After swapping, a = " + a + " b = " + b);

    }
}
