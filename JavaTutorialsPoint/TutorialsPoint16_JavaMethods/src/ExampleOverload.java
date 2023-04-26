public class ExampleOverload {

    private static int minFunction(int n1, int n2) {
        int min;
        min = (n1 > n2) ? n2 : n1;
        return min;
    }

    private static double minFunction(double n1, double n2) {
        double min;
        min = (n1 > n2) ? n2 : n1;
        return min;
    }

    public static void main(String[] args) {
        int a = 11, b = 6;
        double c = 7.3, d = 9.4;

        int result1 = minFunction(a, b);
        double result2 = minFunction(c, d);

        System.out.println("Minimum Value = " + result1);
        System.out.println("Minimum Value = " + result2);

    }
}
