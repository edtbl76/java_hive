package CollectionsInJava.HashSet;

import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.IntStream;

public class HashSetExample1 {

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();

        // Method expression using the add() method of HashSet.
        IntStream.range(0, 10).forEach(set::add);
        System.out.println(set);

        // Check for num 5
        System.out.println("5 is present: " + set.contains(5));

        // remove one.
        set.remove(1);
        System.out.println(set);

        // iterate old school
        Iterator<Integer> iterator = set.iterator();
        while(iterator.hasNext()) {
            System.out.println("Value: " + iterator.next());
        }

        // Iterate new school
        set.forEach(integer -> System.out.println("Value: " + integer));

    }
}
