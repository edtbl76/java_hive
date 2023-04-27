package collections.arraylists;

import utils.Generated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Generated
@SuppressWarnings("all")
public class DemoIterator {

    public static void main(String[] args) {

        // setup
        List<Integer> list = new ArrayList<>(List.of(10, 20, 30, 40, 10));

        // Create Iterator
        Iterator<Integer> iterator = list.iterator();

        // 1. While Loop
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        // 2. forEachRemaining
        // (Don't have to check for next element) <-- Prefer This.
        // NOTE: if I don't re-initialize this iterator, it will be "at the end".
        iterator = list.iterator();
        iterator.forEachRemaining(System.out::print);
        System.out.println();
    }
}
