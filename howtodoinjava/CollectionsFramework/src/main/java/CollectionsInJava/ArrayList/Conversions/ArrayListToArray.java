package CollectionsInJava.ArrayList.Conversions;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListToArray {

    public static void main(String[] args) {

        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,4,5));

        // List.toArray()
        Integer[] integers = nums.toArray(new Integer[0]);
        System.out.println(Arrays.toString(integers));

        // JAVA8 Stream Version
        Integer[] integers1 = nums.stream().toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers1));

    }
}
