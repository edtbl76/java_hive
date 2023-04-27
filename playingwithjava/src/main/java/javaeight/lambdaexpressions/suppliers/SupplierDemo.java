package javaeight.lambdaexpressions.suppliers;


import utils.Generated;

import java.util.Random;
import java.util.function.*;

/*
    Suppliers take no parameters, but produce a value when get() is called.
    - This allows objects to be provided to lambdas/anonymous functions inside the Supplier container.
 */
@Generated
public class SupplierDemo {

    static boolean isPersonEligibleToVote(Supplier<Person> supplier, Predicate<Person> predicate) {
        return predicate.test(supplier.get());
    }

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {

        // setup
        Random random = new Random();
        Supplier<Person> supplier = () -> new Person("Connor", 11);
        Predicate<Person> predicate = p -> p.getAge() > 18;

        // evaluation
        boolean eligible = isPersonEligibleToVote(supplier, predicate);
        System.out.println("Person is eligible for voting: " + eligible);

        // Int Supplier example -> Generating random integer.
        // (This would be the same as Supplier<Integer>)
        IntSupplier intSupplier = () -> random.nextInt() * 10;
        System.out.println(intSupplier.getAsInt());

        // Double Supplier  (Same as Supplier<Double>)
        DoubleSupplier doubleSupplier = () -> random.nextDouble() * 10;
        System.out.println(doubleSupplier.getAsDouble());

        // Long supplier( Same as Supplier<Long>)
        LongSupplier longSupplier = () -> random.nextLong() * 10;
        System.out.println(longSupplier.getAsLong());

        // Boolean supplier (Same as Supplier<Boolean>
        BooleanSupplier booleanSupplier = () -> random.nextInt() * 10 > 10;
        System.out.println(booleanSupplier.getAsBoolean());
    }

}
