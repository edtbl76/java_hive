package CollectionsInJava.Array.Examples;

import java.util.Arrays;

public class CopyArrayRange {

    public static void main(String[] args) {

        String[] names = {"Winken","Blinken","Nod"};

        // Subarray from index 0 to 2 (inclusive/exclusive)
        System.out.println(Arrays.toString(Arrays.copyOfRange(names, 0, 2)));

        // Same thing but using list (Subarray to List)
        System.out.println(Arrays.asList(Arrays.copyOfRange(names, 2, names.length)));

    }
}
