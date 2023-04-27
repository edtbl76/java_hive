package collections.linkedlists;

import utils.Generated;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Integer.valueOf;

@SuppressWarnings("java:S106")
@Generated
public class DemoBasicOperations {

    @SuppressWarnings("all")
    public static void main(String[] args) {

        /*
         *  Creation
         */

        // 1. No args constructor
        List<Integer> list1 = new LinkedList<>();

        // 2. Constructor w/ an old Collection
        /*
            NOTE: This is also the "copy" constructor
         */
        List<Integer> list2 = new LinkedList<>(list1);


        /*
         *  Insertion
         */

        // 1. insertion at end
        list2.add(5);
        list2.add(10);
        System.out.println("add(): " + list2);

        // 2. Inserted at end - explicitly
        /*
            NOTE: AddLast/addFirst is only available directly from LL
         */
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addLast(3);
        linkedList.addLast(4);
        System.out.println("addLast(): " + linkedList);

        // 3. add at index
        list2.add(0, 0);
        list2.add(3, 15);
        System.out.println("add(index, element): " + list2);

        // 4. addFirst
        linkedList.addFirst(2);
        linkedList.addFirst(1);
        System.out.println("addFirst(): " + linkedList);

        // 5. add Collection (automatically at the end)
        list2.addAll(List.of(20, 25, 30));
        System.out.println("addAll(): " + list2);

        // 6. addCollection at an index
        list2.addAll(1, linkedList);
        System.out.println("addAll(index, collection): " + list2);

        /*
         *  Fetching
         */

        // 1. getFirst
        // - Throws NoSuchElementException if empty.
        System.out.println("getFirst(): " + linkedList.getFirst());

        // 2. getLast
        // - Throws NoSuchElementException if empty.
        System.out.println("getLast(): " + linkedList.getLast());

        // 3. get()
        // - Throws IndexOutOfBoundsException if the index doesn't exist.
        System.out.println("get(): " + list2.get(4));

        /*
         *  Remove
         *
         */

        // 1. remove first (throws NoSuchElementException if empty)
        linkedList.removeFirst();
        System.out.println("removeFirst(): " + linkedList);

        // 2. remove Last (throws NoSuchElementException if empty)
        linkedList.removeLast();
        System.out.println("removeLast(): " + linkedList);

        // 3. remove at particular element.
        list2.remove(0);
        System.out.println("remove(index): " + list2);

        // 4. remove particular element
        list2.remove(valueOf(30));
        System.out.println("remove(Object o): " + list2);

        // 5. removeif (in this case divisible by 5)
        list2.removeIf(integer -> (integer % 5 == 0));
        System.out.println("remove(Predicate): " + list2);

        // 6. Reset for next exercise
        list2.addAll(list2);
        list2.addAll(list2);
        linkedList = new LinkedList<>(list2);
        System.out.println("New List: " + list2);

        // 7. Remove first occurence
        linkedList.removeFirstOccurrence(1);
        System.out.println("removeFirstOccurence(): " + linkedList);

        // 8. Remove last occurence
        linkedList.removeLastOccurrence(1);
        System.out.println("removeLastOccurence(): " + linkedList);

        // 9. Remove a collection
        linkedList.removeAll(List.of(2,3));
        System.out.println("removeAll(): " + linkedList);



    }
}
