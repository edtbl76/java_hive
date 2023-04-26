package CollectionsInJava.ListIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class ListIteratorExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F"));

        ListIterator<String> listIterator = list.listIterator();

        // Forward
        System.out.println("Forward Iteration");
        while(listIterator.hasNext())
            System.out.print(listIterator.next() + "->");

        // Backward
        System.out.println("\nBackward Iteration");
        while(listIterator.hasPrevious())
            System.out.print(listIterator.previous() + "<-");

        // From position
        System.out.println("\nIteration from specified position");
        listIterator = list.listIterator(2);
        while(listIterator.hasNext())
            System.out.print(listIterator.next() + ",");
    }
}
