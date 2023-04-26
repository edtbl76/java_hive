import java.util.Arrays;

public class ArrayCopyArraysCopyOf {

    public static void main(String[] args) {

        // Init an array
        int[] array = {4, 6, 8};
        System.out.println("Array : " + Arrays.toString(array));

        /*
            Repeat previous steps using Arrays.copy of
         */
        int[] copyOf = Arrays.copyOf(array, array.length);
        System.out.println("CopyOf: " + Arrays.toString(copyOf));

        copyOf[0]++;
        System.out.println("Array : " + Arrays.toString(array));
        System.out.println("CopyOf: " + Arrays.toString(copyOf));


    }
}
