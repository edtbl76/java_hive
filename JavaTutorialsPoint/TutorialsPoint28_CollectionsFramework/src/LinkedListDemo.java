import java.util.*;

public class LinkedListDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();

        // add stuff
        linkedList.add("F");
        linkedList.add("B");
        linkedList.add("D");
        linkedList.add("E");
        linkedList.add("C");
        linkedList.addFirst("Z");
        linkedList.addLast("A");
        linkedList.add(1, "A2");

        System.out.println("Original contents of ll: " + linkedList);

        // remove some shit
        linkedList.remove("F"); // remove by Object
        linkedList.remove(2); //remove by index
        System.out.println("Contents after deletion: " + linkedList);

        // more removes (the prebaked ones) -- behaves like a Deque
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("Contents after deque-like deletion: " + linkedList);

        // access and mutate
        Object val = linkedList.get(2);
        linkedList.set(2, val + " Changed");
        System.out.print("Contents after change: " + linkedList);
    }
}
