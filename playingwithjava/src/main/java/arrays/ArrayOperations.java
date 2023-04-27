package arrays;

import utils.Generated;

import java.util.Arrays;
import java.util.List;

@Generated
public class ArrayOperations {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {

        /*
         *  Converting an array to a list
         */
        Integer[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Array: " + Arrays.toString(numbers));

        // 1. asList() -> returns a fixed list that keeps a reference to the original Array object
        List<Integer> list = Arrays.asList(numbers);
        System.out.println("List: " + list);

        // change the original list, changes the new list.
        numbers[0] = 0;
        System.out.println("Modified Array: " + Arrays.toString(numbers));
        System.out.println("List w/ Side Effect: " + list);

        // 2. toList() is backed by a copy of the original array
        // (because it's functional! No side effects!!!)
        list = Arrays.stream(numbers).toList();
        numbers[0] = 1;

        // When we change the array back to its initial state, the new list won't revert,
        // because it is backed by the copy.
        System.out.println("Restored Array: " + Arrays.toString(numbers));
        System.out.println("List w/o Side Effect: " + list);

        /*
         *  Check for equality
         */
        Integer[] otherNumbers = {1, 2, 3, 4, 5};

        boolean equal = Arrays.equals(numbers, otherNumbers);
        System.out.println(
                Arrays.toString(numbers) + " is equal to " +
                Arrays.toString(otherNumbers) + ": " + equal);

        /*
         *  Fill w/ a default value
         */
        System.out.println("Initial Array (again) " + Arrays.toString(numbers));
        Arrays.fill(numbers, 0);
        System.out.println("Zeroed array " + Arrays.toString(numbers));



    }
}
