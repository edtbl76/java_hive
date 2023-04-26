package CollectionsInJava.Array.Examples;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class RemoveArrayDuplicates {

    public static void main(String[] args) throws CloneNotSupportedException {

        // Create an array w/ duplicate elements
        Integer[] numbers = new Integer[]{1,2,3,4,5,1,3,5};
        System.out.println("With Duplicates: " + Arrays.toString(numbers));

        // Create Set from Array Elements, then convert it back to an array
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>(Arrays.asList(numbers));
        Integer[] uniqueNumbers = linkedHashSet.toArray(new Integer[] {});

        // Verify
        System.out.println("Without Duplicates: " + Arrays.toString(uniqueNumbers));
    }
}
