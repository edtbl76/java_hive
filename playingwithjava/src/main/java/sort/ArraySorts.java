package sort;


import static utils.Utils.*;

public class ArraySorts {

    private ArraySorts() {}

    // brute force swap sort
    public static void recursiveSwapSort(int[] array, int length) {

        if (length == 1) {
            return;
        }

        /*
            Remember, only iterate up to length - 1
            this ensures we don't go past the length of the array when
            evaluating i + 1
         */
        for (int i = 0; i < length - 1; i++) {
            if (array[i] > array[i + 1]) {
                swap(array, i, i + 1);
            }
        }
        recursiveSwapSort(array, length - 1);
    }

}
