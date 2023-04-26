package CollectionsInJava.LinkedList;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class SortingLinkedList {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        System.out.println("Unsorted: " + linkedList);

        // Sorted
        Collections.sort(linkedList);
        System.out.println("Sorted: " + linkedList);

        // Custom sorting (newer syntax.. preferred)
        linkedList.sort(Collections.reverseOrder());
        System.out.println(linkedList);

        // Custom sorting.
        Collections.sort(linkedList, Collections.reverseOrder());
        System.out.println(linkedList);



    }
}
