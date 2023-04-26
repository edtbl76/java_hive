package CollectionsInJava.CopyOnWriteArraySet;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class COWASExample1 {

    public static void main(String[] args) {
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>(Arrays.asList(1,2,3));
        System.out.println(set);

        // Iter1
        Iterator<Integer> iter1 = set.iterator();

        // add one element and verify the update
        set.add(4);
        System.out.println(set);

        // Iter2
        Iterator<Integer> iter2 = set.iterator();

        // Validate SNAPSHOT

        System.out.println("==== Verify Iterator1 ====");
        iter1.forEachRemaining(System.out::println);

        System.out.println("==== Verify Iterator2 ====");
        iter2.forEachRemaining(System.out::println);

    }
}
