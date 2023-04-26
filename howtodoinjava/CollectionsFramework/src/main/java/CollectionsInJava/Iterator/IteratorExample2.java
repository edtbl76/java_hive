package CollectionsInJava.Iterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class IteratorExample2 {
    public static void main(String[] args) {

        List<String> init = Arrays.asList("A", "B", "C", "D");

        // ArrayList
        ArrayList<String> list = new ArrayList<>(init);
        System.out.println(list);
        list.iterator().forEachRemaining(System.out::println);

        // Set
        HashSet<String> set = new HashSet<>(init);
        System.out.println(set);
        set.iterator().forEachRemaining(System.out::println);


    }
}
