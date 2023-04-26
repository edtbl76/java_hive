package CollectionsInJava.Spliterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Spliterator;

public class SpliteratorSizeExample {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

        Spliterator<String> spliterator = list.spliterator();

        System.out.println(spliterator.estimateSize());
        System.out.println(spliterator.getExactSizeIfKnown());

    }
}
