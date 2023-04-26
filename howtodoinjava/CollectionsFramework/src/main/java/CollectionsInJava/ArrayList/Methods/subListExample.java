package CollectionsInJava.ArrayList.Methods;

import java.util.ArrayList;
import java.util.Arrays;

public class subListExample {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));

        // Create Sublists
        ArrayList<Integer> sublist = new ArrayList<>(list.subList(2, 6));
        ArrayList<Integer> endList = new ArrayList<>(list.subList(5, list.size()));
        System.out.println("Original: " + list);
        System.out.println("Selection: " + sublist);
        System.out.println("Index To End: " + endList);


        // Remove Sublist from original sublist
        list.subList(6,9).clear();
        System.out.println("Removed: " + list);
    }
}
