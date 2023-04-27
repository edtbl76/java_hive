package collections.cows;

import utils.Generated;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Generated
@SuppressWarnings("all")
public class CowIteration {

    public static void main(String[] args) {

        // 1. ForEach
        System.out.println("forEach(): ");
        List<String> animals = new CopyOnWriteArrayList<>(List.of("dog", "cat", "fish"));
        animals.forEach(System.out::println);
        System.out.println();

        // 2. Iteration (demonstrating the internals of COW)
        Iterator<String> iterator = animals.iterator();

        // Not going to be visible to the iterator I just created.
        animals.add("gerbil");

        System.out.println("iterator() (Gerbil Not Seen): ");
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();

        // Recreate the iterator, this time showing the gerbil.
        System.out.println("iterator() (Gerbil!): ");
        iterator = animals.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();


        // Calling remove doesn't result in ConcurrentModificationException
        iterator = animals.iterator();
        while(iterator.hasNext()) {
            /*
                The reason this works is because of the COW internals.

                The iterator for a COW doesn't have a remove() method because it would end
                up removing the elements from the iteration snapshot, without affecting the
                actual backing array that holds the data.
             */
            animals.remove("gerbil");
        }

        System.out.println("removed gerbil!");
        iterator = animals.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();

    }
}
