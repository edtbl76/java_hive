package javaeight.lambdaexpressions.consumers;

import utils.Generated;

import java.util.function.*;

import static java.lang.Math.*;

@Generated
public class ConsumerDemo {

    /*
        Consumer<T>
        - opposite of Supplier<T>
        - it accepts a parameter of type T, and it returns nothing.

        - used in all scenarios where an object needs to be consumed
            - taken as input
            - void operations are performed against the object.

            accept(T t):
                - performs the operation on the given argument

            andThen(Consumer after)
                - returns a composed Consumer that performs (in sequence) this operation
                followed by the operation passed in as a parameter


        BiConsumer<T, U>
        - same concept, but it accepts two arguments to interact w/ each other.
     */
    @SuppressWarnings("java:S106")
    public static void main(String[] args) {

        // Consumers
        Consumer<String> stringConsumer = System.out::println;
        IntConsumer integerConsumer = integer -> System.out.println(integer + " squared is " + integer * integer);
        DoubleConsumer doubleConsumer = value -> System.out.println("The square root of " + value + " is " + sqrt(value));
        LongConsumer longConsumer = value -> System.out.println(value + " to the 10th power is " + pow(value, 10));

        // accept() example
        stringConsumer.accept("Hello!");
        integerConsumer.accept(5);
        doubleConsumer.accept(5);
        longConsumer.accept(2);

        // andThen() Example.
        stringConsumer
                .andThen(stringConsumer)
                .accept("Hello ");


        // BiConsumer
        BiConsumer<String, String> biConsumer = (s1, s2) -> System.out.println(s1 + ", " + s2);
        biConsumer.accept("Hello", "World");

    }
}
