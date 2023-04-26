package CollectionsInJava.Spliterator;

import java.util.Arrays;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class SpliteratorGetComparatorExample {
    public static void main(String[] args) {
        SortedSet<String> set = new TreeSet<>(Collections.reverseOrder());
        set.addAll(Arrays.asList("A", "D", "C", "B"));

        System.out.println(set);
        System.out.println(set.spliterator().getComparator());
    }
}
