package CollectionsInJava.Array.Examples;

import java.util.Arrays;

public class RemoveArrayDuplicatesWithTempArrayWhenNotSorted {

    public static void main(String[] args) throws CloneNotSupportedException {

        // Array w/ duplicate elements
        Integer[] originalArray = new Integer[]{1,1,2,3,3,3,4,5,6,6,6,7,8};
        System.out.println(Arrays.toString(originalArray));

        Integer[] tempArray = removeDuplicates(originalArray);
        System.out.println(Arrays.toString(tempArray));

    }

    private static Integer[] removeDuplicates(Integer[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].equals(arr[i + 1])) {
                arr[i] = null;
            }
        }

        return arr;
    }
}
