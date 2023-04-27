package arrays;

import utils.Generated;

import java.util.Arrays;

import static java.util.Comparator.comparingInt;

@Generated

public class ArraySearch {

    @SuppressWarnings({"java:S106", "java:S1192"})
    public static void main(String[] args) {


        /*
         *  Arrays.binarySearch()
         *  - if array isn't sorted, result is undefined
         *  - if element is present, it's index is returned
         *      - else, the index of the first element greater than the key is returned (that's dumb)
         *  - if multiple elements exist, no guarantees about which one is found.
         *  - if search key isn't the same type, throws ClassCastException
         */
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println("Array: " + Arrays.toString(numbers));

        // 1. simple binarysearch
        int index = Arrays.binarySearch(numbers, 4);
        System.out.println("Index of element 4 is " + index);

        // 2. if we know it exists within a certain range, we can provide the range
        index = Arrays.binarySearch(numbers, 5, 9, 4);
        System.out.println("Index of element 4 is " + index);

        index = Arrays.binarySearch(numbers, 1, 5, 4);
        System.out.println("Index of element 4 is " + index);

        // 3. providing a comparator to search custom objects.
        Thing[] things = {
                new Thing(1, "Kerfluffle"),
                new Thing(2, "Thingamabob"),
                new Thing(3, "Doohickey"),
                new Thing(4, "Wooleybooger")
        };

        index = Arrays.binarySearch(
                things,
                new Thing(3, "Doohickey"),
                comparingInt(Thing::getId)
        );
        System.out.println("The index of the Doohickey is " + index);
    }
}
