package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Spliterator;

public class SpliteratorForEachRemainingExample {

    public static void main(String[] args) {
        ArrayList<Integer> digits = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

        Spliterator<Integer> spliterator = digits.spliterator();

        // This is like doing a "while hasNext(), next() loop)
        spliterator.forEachRemaining(System.out::println);
    }
}
