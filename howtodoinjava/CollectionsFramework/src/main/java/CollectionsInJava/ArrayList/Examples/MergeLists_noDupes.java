package CollectionsInJava.ArrayList.Examples;

import java.util.*;

public class MergeLists_noDupes {

    public static void main(String[] args) {

        ArrayList<String> listOne = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        ArrayList<String> listTwo = new ArrayList<>(Arrays.asList("a", "b", "c", "f", "g"));
        System.out.println(listOne + "\n" + listTwo);

        // METHOD ONE (LinkedHashSet)
        Set<String> set = new LinkedHashSet<>(listOne);
        set.addAll(listTwo);
        List<String> combinedList = new ArrayList<>(set);
        System.out.println(combinedList);

        // METHOD TWO (Use removeAll to get the difference of the two lists... (i.e. ignoring what is the same)
        // Then we add that back to the original list. This is a convoluted, but clever way of doing it w/o using a
        // Set.
        List<String> listTwoCopy = new ArrayList<>(listTwo);
        listTwoCopy.removeAll(listOne);
        listOne.addAll(listTwoCopy);
        System.out.println(listOne);
    }
}
