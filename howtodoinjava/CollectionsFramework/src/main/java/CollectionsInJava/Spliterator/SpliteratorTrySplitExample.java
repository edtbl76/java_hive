package CollectionsInJava.Spliterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Spliterator;

public class SpliteratorTrySplitExample {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));
        System.out.println(list);

        Spliterator<String> spliterator1 = list.spliterator();
        Spliterator<String> spliterator2 = spliterator1.trySplit();

        spliterator1.forEachRemaining(System.out::println);

        System.out.println("========");

        spliterator2.forEachRemaining(System.out::println);
    }
}
