package CollectionsInJava.Array.Examples;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RemoveArrayDuplicatesWithTempArrayWhenSorted {

    public static void main(String[] args) throws CloneNotSupportedException {

        // Sorted Array w/ dupes
        Integer[] origArray = new Integer[]{1,1,2,3,3,3,4,5,6,6,6,7,8};
        System.out.println(Arrays.toString(origArray));

        // Create + verify temp
        Integer[] tempArray = removeDuplicates(origArray);
        System.out.println(Arrays.toString(tempArray));
    }

    private static Integer[] removeDuplicates(Integer[] origArray) {
        Integer[] tempArray = new Integer[origArray.length];

        int j = 0;
        for(int i = 0; i < origArray.length - 1; i++) {
            Integer current = origArray[i];
            if (!current.equals(origArray[i + 1])) {
                tempArray[j++] = current;
            }
        }

        tempArray[j++] = origArray[origArray.length - 1];
        return tempArray;
    }
}
