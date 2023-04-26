import java.util.*;

@SuppressWarnings("unchecked")
public class CollectionsAlgorithmsDemo {

    public static void main(String[] args) {

        // Create + Init Linked List
        LinkedList ll = new LinkedList();
        ll.add(-8);
        ll.add(20);
        ll.add(-20);
        ll.add(8);

        // print it out
        System.out.println("LinkedList after init: " + ll);

        // Re Sort in Reverse Order
        Comparator r = Collections.reverseOrder();
        ll.sort(r);

        Iterator li = ll.iterator();
        System.out.print("List sorted in reverse: ");

        while(li.hasNext()) {
            System.out.print(li.next() + " ");
        }
        System.out.println();

        // randomize, reinit the iterator and dump out the random nums
        Collections.shuffle(ll);
        li = ll.iterator();
        System.out.print("List shuffled: " );

        while(li.hasNext()) {
            System.out.print(li.next() + " ");
        }
        System.out.println();

        // Min/mac
        System.out.println("Minimum: " + Collections.min(ll));
        System.out.println("Maximum: " + Collections.max(ll));

    }
}
