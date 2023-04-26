import java.util.Date;

public class UnaryOperators {
    public static void main(String[] args) {

        // variables
        int positiveNumber = 10;
        long date = new Date().getTime();


        // Output
        System.out.println("Original Value: " + positiveNumber);
        System.out.println("Unary Minus: " + -positiveNumber);

        /*
            Similar example I used before.
            Instead of using a ternary operator to make the true condition even, and false odd, I've swapped
            those conditions by applying a Unary Operator.
         */
        String msg = !(date % 2 == 0) ? "Truly Odd Maude" : "Falsely Even Steven";
        System.out.println(date + " is " + msg);


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
