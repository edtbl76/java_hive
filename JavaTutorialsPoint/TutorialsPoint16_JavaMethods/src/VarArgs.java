public class VarArgs {

    private static void printMax(double... numbers) {
        if(numbers.length == 0) {
            System.out.println("No arguments passed");
        }

        double result = numbers[0];

        for ( double number : numbers) {
            if (number > result) {
                result = number;
                System.out.println("The max value is " + result);
            }
        }
    }

    public static void main(String[] args) {
        printMax(34, 3, 3, 2, 56.5);
        //noinspection RedundantArrayCreation
        printMax(new double[]{1, 2, 3}); // this creates a warning for Redundant Array Creation.
    }
}
