package CollectionsInJava.ArrayList.Conversions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class ArrayListToLinkedList {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        LinkedList<Integer> linkedList = new LinkedList<>(arrayList);
        System.out.println(linkedList);
    }
}
