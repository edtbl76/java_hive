package collections.arraylists;

/*
    ListIterator solves some of the challenges of
    a basic iterator.
    1.) Iterator can only move forward
    2.) can't update / insert data into list in Iterator.
 */

import utils.Generated;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Generated
@SuppressWarnings("all")
public class DemoListIterator {

    public static void main(String[] args) {

        // Setup
        List<Integer> list = new ArrayList<>(List.of(10, 20, 30 ,40));

        // Getting the List Iterator
        ListIterator<Integer> iterator = list.listIterator();

        // Traversing elements.
        System.out.println("Forward Iteration: ");
        while(iterator.hasNext()) {
            System.out.print("Next Element: " + iterator.next());
            System.out.println(", Next Index: " + iterator.nextIndex());
        }

        System.out.println("Backward Iteration: ");
        while(iterator.hasPrevious()) {
            System.out.print("Previous Element: " + iterator.previous());
            System.out.println(", Previous Index: " + iterator.previousIndex());
        }
    }

}
