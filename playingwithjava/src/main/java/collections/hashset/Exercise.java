package collections.hashset;

import utils.Generated;

import java.util.HashSet;
import java.util.Set;

@Generated
@SuppressWarnings("java:S106")
public class Exercise {

    public static void main(String[] args) {

        int[] data = { 12, 34, 1, 56, 43, 34, 65};
        int duplicate = 0;

        Set<Integer> set = new HashSet<>();
        for (int i : data) {
            if (!set.add(i)) {
                duplicate = i;
                break;
            }
        }


        System.out.println(duplicate);
    }
}
