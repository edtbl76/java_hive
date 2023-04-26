package CollectionsInJava.ArrayList.Examples;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class RemovingDuplicates {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(nums);

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(nums);
        ArrayList<Integer> numsWithoutDupes = new ArrayList<>(hashSet);

        System.out.println(numsWithoutDupes);

        // OneLiner
        System.out.println(new ArrayList<>(new LinkedHashSet<>(nums)));
    }
}
