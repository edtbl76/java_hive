package CollectionsInJava.ArrayList.Examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemovingDupes_Java8 {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        System.out.println(nums);

        List<Integer> listWithoutDuplicates = nums.stream().distinct().collect(Collectors.toList());
        System.out.println(listWithoutDuplicates);

        // Oneliner
        System.out.println(nums.stream().distinct().collect(Collectors.toList()));
    }
}
