package javaeight.lambdaexpressions.functionsandoperators;

import utils.Generated;

import java.util.function.BinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

@Generated
@SuppressWarnings("java:S106")
public class OperatorDemo {

    /*
        UnaryOperator<T>
            - T apply(T t)

        DoubleUnaryOperator
            - double applyAsDouble(double operand)

        IntUnaryOperator
            - int applyAsInt(int operand)

        LongUnaryOperator
            - long applyAsLong(long operand)


            ----

        BinaryOperator<T> inherits from BiFunction<T, T, T>

        BinaryOperator<T>       T apply(T t, T u)

        IntBinaryOperator       int applyAsInt(int left, int right)

        DoubleBinaryOperator    double applyAsDouble(double left, double right)

        LongBinaryOperator      long applyAsLong(long left, long right)

     */
    public static void main(String[] args) {

        // setup
        Person person = new Person();
        Person person1 = new Person("Russell Wilson", 3);
        Person person2 = new Person("Geno Smith", 7);

        // Generic UnaryOperator used as a way to collect variables and instantiate an object.
        // (Cool trick to mix oop and functional coding)
        UnaryOperator<Person> operator = p -> {
            p.setName("Michael");
            p.setAge(22);
            return p;
        };

        operator.apply(person);
        System.out.println(person);

        // IntUnaryOperator
        IntUnaryOperator intUnaryOperator = operand -> operand * operand;
        System.out.println(intUnaryOperator.applyAsInt(25));

        // Binary Operator to replace one w/ the other.
        BinaryOperator<Person> binaryOperator = (p1, p2) -> {
            p1.setName(p2.getName());
            p1.setAge(p2.getAge());
            return p1;
        };

        binaryOperator.apply(person1, person2);
        System.out.println(person1);
    }
}
