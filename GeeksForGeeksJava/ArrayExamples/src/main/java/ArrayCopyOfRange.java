import java.util.Arrays;

@SuppressWarnings("ALL")
public class ArrayCopyOfRange {

    public static void main(String[] args) {

        // Init an array
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("Array : " + Arrays.toString(array));

        /*
            Repeat previous steps using Arrays.copyOfRange()
         */
        int[] copyOf = Arrays.copyOfRange(array, 2, 5);
        System.out.println("CopyOf: " + Arrays.toString(copyOf));

        copyOf[0]++;
        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("CopyOf: " + Arrays.toString(copyOf));


    }
}
