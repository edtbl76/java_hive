package CollectionsInJava.CopyOnWriteArrayList;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class COWALExample1 {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{1,2,3});
        System.out.println(list);

        //  Get iterator 1
        Iterator<Integer> iterator1 = list.iterator();

        // add an element and verify list is updated
        list.add(4);
        System.out.println(list);

        // Get iter 2
        Iterator<Integer> iterator2 = list.iterator();

        // This demonstrates that each iterator has its own clone of the list.
        System.out.println("====Verify Iterator 1 content====");
        iterator1.forEachRemaining(System.out::println);

        System.out.println("====Verify Iterator 2 content====");
        iterator2.forEachRemaining(System.out::println);

    }
}
