public class methodEx {

    private static int minimumOfTwoNumbers(int n1, int n2) {
        int min;
        min = (n1 > n2) ? n2 : n1;
        return min;
    }

    public static void main(String[] args) {
        int num1 = 10, num2 = 30;
        System.out.println("The smaller number of " + num1 + " and " + num2 + " is " + minimumOfTwoNumbers(num1, num2));
    }
}
