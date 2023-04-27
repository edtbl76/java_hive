package recursion;

public class RecursionWithArrays {

    private RecursionWithArrays() {}

    public static int firstIndexOf(int[] array, int searchTerm, int index) {

        // base case 1, not found
        if (array.length == index) {
            return -1;
        // base case 2, is found
        } else if (array[index] == searchTerm) {
            return index;
        // recurse
        } else {
            return firstIndexOf(array, searchTerm, index + 1);
        }

    }

    public static void invertArrayElements(int[] array, int index) {
        if (index < array.length / 2) {
            int temp = array[index];
            array[index] = array[array.length - 1 - index];
            array[array.length - 1 - index] = temp;

            invertArrayElements(array, index + 1);
        }
    }

    public static int sumArray(int[] array, int length) {
        if (length <= 0) {
            return 0;
        }

        return array[length - 1] + sumArray(array, length - 1);
    }

}
