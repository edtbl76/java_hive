package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Spliterator;

public class SpliteratorTrySplitExample {

    public static void main(String[] args) {
        ArrayList<Integer> digits = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

        Spliterator<Integer> s1 = digits.spliterator();
        Spliterator<Integer> s2 = s1.trySplit();

        System.out.println("Size: " + s1.getExactSizeIfKnown());
        s1.forEachRemaining(System.out::println);

        System.out.println("=====");

        System.out.println("Size: " + s2.getExactSizeIfKnown());
        s2.forEachRemaining(System.out::println);

    }
}
