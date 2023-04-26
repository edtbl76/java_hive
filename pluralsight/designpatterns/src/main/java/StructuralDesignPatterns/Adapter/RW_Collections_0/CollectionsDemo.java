package StructuralDesignPatterns.Adapter.RW_Collections_0;

import java.util.Arrays;
import java.util.List;

public class CollectionsDemo {

    public static void main(String[] args) {
        Integer[] arrayOfIntegers = new Integer[]{42, 43, 44};
        List<Integer> listOfIntegers = Arrays.asList(arrayOfIntegers);

        System.out.println(arrayOfIntegers);
        System.out.println(listOfIntegers);
    }
}
