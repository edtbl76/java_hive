package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ElementSwapping {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        System.out.println(nums);

        for(int i = 0; i < nums.size() - 1;i++) {
            Collections.swap(nums, i, i + 1);
        }

        System.out.println(nums);
    }
}
