package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ComparingLists {

    public static void main(String[] args) {
        ArrayList<String> listOne = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e"));
        ArrayList<String> listTwo = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "f"));

        // It's easier to compare sorted Collections
        Collections.sort(listOne);
        Collections.sort(listTwo);

        System.out.println(listOne.equals(listTwo));        // false

        // make the lists equal by replacing the last element of one with the other
        listTwo.set(listTwo.size() - 1, listOne.get(listOne.size() - 1));
        System.out.println(listOne.equals(listTwo));        // true

    }
}
