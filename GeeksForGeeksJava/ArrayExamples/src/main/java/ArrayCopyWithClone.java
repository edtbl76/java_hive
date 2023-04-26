import java.util.Arrays;

public class ArrayCopyWithClone {

    public static void main(String[] args) {

        // Start the same as ArrayCopy**
        int[] array = {3,5,7};
        System.out.println("Array: " + Arrays.toString(array));

        /*
            copy it w/ clone()
            - print it.
            - modify it
            - print 'em both

            NOTE: This is easier and more readable than iterating through both arrays and copying the
            values from one to the other by hand.
         */
        int[] clone = array.clone();
        System.out.println("Clone: " + Arrays.toString(clone));

        clone[0]++;
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Clone: " + Arrays.toString(clone));


    }
}
