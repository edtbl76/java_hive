import java.util.Arrays;

@SuppressWarnings("ALL")
public class ArrayCopySystemArraycopy {

    public static void main(String[] args) {

        // Init an array
        int[] array = {5, 7, 9};
        System.out.println("Array: " + Arrays.toString(array));

        /*
            Repeat previous steps using System.arraycopy()
         */
        int[] copy = new int[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);

        System.out.println("Copy : " + Arrays.toString(copy));

        copy[0]++;
        System.out.println("Array: " + Arrays.toString(array));
        System.out.println("Copy : " + Arrays.toString(copy));


    }
}
