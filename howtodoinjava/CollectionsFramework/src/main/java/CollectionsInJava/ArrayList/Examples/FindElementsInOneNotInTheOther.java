package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;

public class FindElementsInOneNotInTheOther {

    public static void main(String[] args) {
        ArrayList<String> listOne = new ArrayList<>(Arrays.asList("A","B","C","D"));
        ArrayList<String> listTwo = new ArrayList<>(Arrays.asList("C","D","E","F"));

        System.out.println("One: " + listOne);
        System.out.println("Two: " + listTwo);

        /*
            This will remove all of the elements in listOne, that also exist in listTwo.
            The result is
                - the ADDITIONAL ELEMENTS from the list's METHOD CALL that weren't in the ARGUMENT list
                    or
                - the MISSING ELEMENTS from the ARGUMENT list that are in the METHOD CALL.
         */
        listOne.removeAll(listTwo);
        System.out.println(listOne);

        // Reset
        listOne = new ArrayList<>(Arrays.asList("A","B","C","D"));
        System.out.println("One: " + listOne);
        System.out.println("Two: " + listTwo);

        /*
            This removes all elements in listTwo, that also exist in listOne.
                   The result is
                - the ADDITIONAL ELEMENTS from the list's METHOD CALL that weren't in the ARGUMENT list
                    or
                - the MISSING ELEMENTS from the ARGUMENT list that are in the METHOD CALL.
         */
        listTwo.removeAll(listOne);
        System.out.println(listTwo);

        // RESET
        listTwo = new ArrayList<>(Arrays.asList("C","D","E","F"));
        System.out.println("One: " + listOne);
        System.out.println("Two: " + listTwo);

        // Common Elements (since it finds the INTERSECTION of the two, it works either way)
        listOne.retainAll(listTwo);
        System.out.println(listOne);



    }
}
