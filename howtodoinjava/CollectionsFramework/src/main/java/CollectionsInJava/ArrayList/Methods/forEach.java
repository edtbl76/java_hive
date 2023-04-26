package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class forEach {

    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

        // Method Expression
        names.forEach(System.out::println);

        // Lambda
        names.forEach(s -> System.out.println(s.toLowerCase()));

        // Consumer
        Consumer<String> lambdaExpression = x -> System.out.println(x.toLowerCase());
        names.forEach(lambdaExpression);

        // make your own
        names.forEach(forEach::printString);
    }

    private static void printString(String str) {
        System.out.println(str.toLowerCase());
    }
}
