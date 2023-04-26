package CollectionsInJava.Array.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PrintingContentsOfArray {

    public static void main(String[] args) {

        // Normal List having four String objects
        List<String> list = new ArrayList<>();
        list = Arrays.asList("First", "Second", "Third", "Fourth");

        // print the list
        System.out.println(list);


        // Create a String[] from the elements of list
        String[] array = new String[list.size()];
        // have to copy to a final variable for lambdas.
        List<String> finalList = list;
        IntStream.range(0, list.size()).forEach(value -> array[value] = finalList.get(value));

        System.out.println(array.toString());   // Yes, I want to print the location.. to demonstrate that this is wrong
        System.out.println(Arrays.toString(array)); // Thi sis the correct syntax.      CORRECT

        String[] arr1 = new String[]{"Fifth", "Sixth"};
        String[] arr2 = new String[]{"Seventh", "Eight"};

        // Create 2D Array
        String[][] arrayOfArray = new String[][]{arr1, arr2};

        System.out.println(arrayOfArray);                   // This prints the object and addy of the top first level array
        System.out.println(Arrays.toString(arrayOfArray));  // This prints the obj and addies of next level array
        System.out.println(Arrays.deepToString(arrayOfArray));  // this prints the elements of the arrays. CORRECT
    }
}
