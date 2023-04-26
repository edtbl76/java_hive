package CollectionsInJava.LinkedHashSet;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class LinkedHashSetExample {

    public static void main(String[] args) {
        LinkedHashSet<String> set = new LinkedHashSet<>(Arrays.asList("A", "B", "C", "D", "E"));
        System.out.println(set);

        System.out.println(set.contains("A"));

        set.remove("E");
        System.out.println(set);

        Iterator<String> iterator = set.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        set.forEach(System.out::println);

    }
}
