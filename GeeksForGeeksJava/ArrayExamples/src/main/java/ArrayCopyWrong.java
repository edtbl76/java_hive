import java.util.Arrays;

public class ArrayCopyWrong {

    public static void main(String[] args) {

        // Create an array and print the contents
        int[] array = {1, 3, 5};
        System.out.println("Contents of array: " + Arrays.toString(array));

        // Create an empty array of the same length and assign the previous array to it
        int[] copy;
        copy = array;

        // print the contents
        System.out.println("Contents of copy : " + Arrays.toString(copy));

        // Seems ok.
        // Let's modify the first element in the copy
        copy[0]++;

        // Now print both arrays
        System.out.println("Contents of array: " + Arrays.toString(array));
        System.out.println("Contents of copy : " + Arrays.toString(copy));

        /*
            The element was modified in BOTH structures.
            This is because the assignment operator above did NOT copy the elements over.
            - Instead, we just copied the references from array to copy, which means that both
            structures are referring to the same memory location.
         */
    }


}
