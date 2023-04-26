import java.util.Arrays;

public class ArrayComparisons {

    public static void main(String[] args) {

        int[] array1 = {1, 2, 3};
        int[] array2 = {1, 2, 3};

        /*
            This doesn't work:
            - even though the values of the CONTENTS of the two arrays are the same, the arrays themselves are
            references to two different objects.

            - these aren't equal, because we are comparing two separate references.
         */
        String msg = (array1 == array2) ? "Equal" : "Not Equal";
        System.out.println("Array1 = Array2: " + msg);

        /*
            OPTION 1:

            compare elements one at a time...
            This is kind of a bastardization of the transitive property, whereby.. if all of the contents of the
            two arrays are equal, then the arrays are equal.

            It's convoluted and not all that easy on the eyes.
         */
        for (int i = 0; i < array1.length; i++) {
            System.out.println(array1[i] + " = " + array2[i] + " = " + (array1[i] == array2[i]));
        }

        /*
            Option 2:

            Arrays.equals()
            - in the first example we can see that it works correctly, and is able to compare a 1D Array
            - However it is only capable of SHALLOW comparisons (it fails the second Example)
         */
        System.out.println("Array1 = Array2: " + Arrays.equals(array1, array2));

        int[][] mdArray1 = {{1, 2}, {3, 4}};
        int[][] mdArray2 = {{1, 2}, {3, 4}};
        //noinspection ArrayObjectsEquals
        System.out.println("MDArray1 = MDArray2: " + Arrays.equals(mdArray1, mdArray2));

        /*
            Option 3:

                For deep comparisons of MD arrays...
                Arrays.deepEquals()
         */
        System.out.println("MDArray1 = MDArray2: " + Arrays.deepEquals(mdArray1, mdArray2));
    }
}
