package arrays;

import utils.Generated;

import java.util.Arrays;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.copyOfRange;


@Generated
public class ArrayCopy {

    @SuppressWarnings("java:S106")
    public static void main(String[] args) {

        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Initial array: " + Arrays.toString(numbers));

        // 1. If copied array is same size:
        int[] copied = copyOf(numbers, numbers.length);
        System.out.println("Copied array: " + Arrays.toString(copied));

        // 2. Bigger size fills it w/ 0's
        int[] bigger = copyOf(numbers, 20);
        System.out.println("Bigger copied array: " + Arrays.toString(bigger));

        //3. Copy of range (array, fromIndex, toIndex)
        int[] range = copyOfRange(bigger, 0, 10);
        System.out.println("Range copied array: " + Arrays.toString(range));

        //4. Proving out a Deep Copy
        // Create array of things
        Thing[] things = {
                new Thing(1, "fork"),
                new Thing(2, "spoon"),
                new Thing(3, "knife")
        };
        System.out.println("Initial Array of Things: " + Arrays.toString(things));


        // Copy that bad boy
        Thing[] thingCopies = copyOf(things, things.length);

        // Change knife to a dagger
        things[2] = new Thing(3, "dagger");

        System.out.println("Original Value: " + things[2].getName());
        System.out.println("Copied Value: " + thingCopies[2].getName());



    }
}
