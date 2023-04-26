public class ArithmeticOperators {
    public static void main(String[] args) {

        // Initializing Variable for use.
        int number1 = 10;
        int number2 = 20;


        // Addition Operator Example
        int sum = number1 + number2;
        System.out.println(number1 + " + " + number2 + " = " + sum);

        //Subtraction
        int difference = number2 - number1;
        System.out.println(number2 + " - " + number1 + " = " + difference);

        // Multiplication
        int product = number1 * number2;
        System.out.println(number1 + " x " + number2 + " = " + product);

        // Division
        int quotient = number2 / number1;
        System.out.println(number2 + " / " + number1 + " = " + quotient);

        // Modulo
        int remainder = number1 % number2;
        System.out.println(number1 + " % " + number2 + " = " + remainder);


        /*
            Post Increment and Decrement
            - First we display the unaltered value
            - Second we display the operation
                - for post operations, the original value is returned, and then the operation is performed (so there is
                no change to the value)
            - Last we display the variable again, with the changed value
         */
        int original = 10;
        System.out.println("Original: " + original + " PostIncrement: " + original++ + " AfterOperation: " + original);

        original = 10;
        System.out.println("Original: " + original + " PostDecrement: " + original-- + " AfterOperation: " + original);

          /*
            Pre Increment and Decrement
            - First we display the unaltered value
            - Second we display the operation
                - for pre operations, we perform the calculation first, which means that the new value is
                returned
            - Last we display the variable again, however w/ pre operations this is redundant because the
            operation returned the new value.
         */
        original = 10;
        System.out.println("Original: " + original + " PreIncrement: " + ++original + " AfterOperation: " + original);

        // Pre Decrement
        original = 10;
        System.out.println("Original: " + original + " PreDecrement: " + --original + " AfterOperation: " + original);
    }
}
