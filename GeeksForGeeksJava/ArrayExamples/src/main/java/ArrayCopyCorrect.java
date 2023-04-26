import java.util.Arrays;

public class ArrayCopyCorrect {

    public static void main(String[] args) {

        // Start the same way as ArrayCopyWrong.
        int[] array = {2, 4, 6};
        System.out.println("Array: " + Arrays.toString(array));

        // Create the empty array
        int[] copy = new int[array.length];

        /*
            SECRET SAUCE!
            - You must copy all of the elements over "by hand"
         */
        //noinspection ManualArrayCopy
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        // Print it out.
        System.out.println("Copy : " + Arrays.toString(copy));

        // Modify the first element of the copy and then print both out.
        copy[0]++;
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Copy : " + Arrays.toString(copy));

    }
}
