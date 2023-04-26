package CollectionsInJava.ArrayList.Conversions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class LinkedListToArrayList {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(1,2,3,4,5));
        System.out.println(linkedList);

        ArrayList<Integer> integers = new ArrayList<>(linkedList);
        System.out.println(integers);
    }
}
