package CollectionsInJava.LinkedList;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListMethods {

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();

        // Add
        linkedList.add("added");
        linkedList.add(1, "inserted");
        System.out.println(linkedList);

        // AddFirst & AddLast
        linkedList.addFirst("First");
        linkedList.addLast("Last");
        System.out.println(linkedList);

        // size()
        System.out.println("Size: " + linkedList.size());

        // contains
        System.out.println("Ed?: " + linkedList.contains("Ed"));
        System.out.println("added?: " + linkedList.contains("added"));

        // add duplicate
        linkedList.add("added");
        System.out.println(linkedList);

        // remove (only removes the first instance)
        linkedList.remove("added");
        System.out.println(linkedList);

        // remove (no args just grabs the first element)
        linkedList.remove();
        System.out.println(linkedList);

        // dupe
        linkedList.addLast("Last");
        System.out.println(linkedList);

        // get the first and last elements
        System.out.println(linkedList.getFirst());
        System.out.println(linkedList.getLast());

        // Get index of FIRST occurence (returns -1 if it isn't there)
        System.out.println(linkedList.indexOf("Last"));
        System.out.println(linkedList.indexOf("Superman!"));

        // Get index of LAST occurence (also returns -1 if not there)
        System.out.println(linkedList.lastIndexOf("Last"));

        // Iterate! (we could use iterator or List iterator)
        Iterator<String> iterator = linkedList.iterator();
        iterator.forEachRemaining(System.out::println);

    }
}
