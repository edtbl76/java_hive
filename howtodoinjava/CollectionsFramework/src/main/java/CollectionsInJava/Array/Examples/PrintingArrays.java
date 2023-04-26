package CollectionsInJava.Array.Examples;

import java.util.Arrays;

public class PrintingArrays {

    public static void main(String[] args) {

        int[][] coordinates = {{1,2,3},{4,5,6,},{7,8,9}};

        // old way (fancy)
        for(int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates.length; j++) {
                System.out.print(coordinates[i][j] + " ");
            }
            System.out.println();
        }

        // newer way,  prints the whole damn thing.
        System.out.println(Arrays.deepToString(coordinates));
    }
}
