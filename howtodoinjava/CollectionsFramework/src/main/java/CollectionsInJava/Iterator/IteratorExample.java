package CollectionsInJava.Iterator;

import java.util.*;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> init = Arrays.asList("A", "B", "C", "D");

        // ArrayList
        ArrayList<String> list = new ArrayList<>(init);
        System.out.println(list);

        Iterator<String> iterator = list.iterator();
        iterate(iterator);
        System.out.println(list.isEmpty());

        // HashSet
        HashSet<String> set = new HashSet<>(init);
        System.out.println(set);

        iterate(set.iterator());    // simpler syntax
        System.out.println(set.isEmpty());

        // HashMap
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        System.out.println(map);
        iterate(map.keySet().iterator());
        System.out.println(map.isEmpty());
    }



    private static void iterate(Iterator<?> iterator) {
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
    }
}
