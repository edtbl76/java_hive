package javaeight.lambdaexpressions.functionsandoperators;

import utils.Generated;

import java.util.function.*;

@Generated
@SuppressWarnings("java:S106")
public class FunctionDemo {

    /*
        Function<T, R>
        - functional interface that takes a type T, and returns type R
        - (This is the combination of a Consumer and a Supplier)

     */

    public static void main(String[] args) {

        /*
          * Functions + BiFunctions
          * - Functions can apply(), compose() and andThen()
          * - BiFunctions can apply() and andThen()

         */

        // Function<Integer, T> == ToIntFunction<T>
        ToIntFunction<String> lengthFunction = String::length;
        ToDoubleFunction<Integer> squareRoot = Math::sqrt;

        // IntUnaryOperator, etc.
        IntUnaryOperator addTen = operand -> operand + 10;
        IntUnaryOperator doubleMe = operand -> operand * 2;

        // BinaryOperator<R> == BiFunction<T, T, R>
        // or <R>BinaryOperator = BinaryOperator<R> (i.e. IntBinaryOperator == BinaryOperator<Integer>
        IntBinaryOperator add = Integer::sum;

        /*
            R apply(T t)
                Takes argument T and returns R.
         */
        System.out.println("Length of string: " + lengthFunction.applyAsInt("This is awesome!"));
        System.out.println("Square Root of 10 is : " + squareRoot.applyAsDouble(10));
        System.out.println("The sum of 2 and 3 is " + add.applyAsInt(2, 3));

        /*
            compose(Function<? super V, ? extends T> before
            - returns a composed function that
                1. applies the parameter function on the input first
                2. applies the function on which it is called to the result of the parameter function
         */
        System.out.println("compose() result : " + addTen.compose(doubleMe).applyAsInt(3));

        /*
            andThen()
                - works the same as it does for consumers.
         */
        System.out.println("andThen() result: " + addTen.andThen(doubleMe).applyAsInt(3));


    }
}
