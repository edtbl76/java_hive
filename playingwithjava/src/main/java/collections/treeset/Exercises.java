package collections.treeset;


import utils.Generated;

import java.util.TreeSet;

@Generated
@SuppressWarnings("java:S106")
public class Exercises {

    public static void main(String[] args) {

        // 1. find numbers greater than 50
        int[] numbers = {1,4,5,2,34,66,5,4,33,45,6,8,56,76,78,98,34,37,12,12,23,43,54,56};
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i : numbers) {
            treeSet.add(i);
        }
        System.out.println(treeSet.tailSet(50));
        treeSet.clear();

        // 2: Smallest and Largest number in an array
        numbers = new int[]{1, 4, 5, 2, 34, 66, 5, 4, 33, 45, 6, 8, 56, 76, 78, 98, 34, 37, 12, 12, 23, 43, 54, 56};
        for (int i : numbers) {
            treeSet.add(i);
        }
        System.out.println("First: " + treeSet.first());
        System.out.println("Last : " + treeSet.last());



    }
}
