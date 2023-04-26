package CollectionsInJava.Array;


import java.util.Arrays;

public class ArrayTypes {

    public static void main(String[] args) {

        // single dimension array
        String[] names = {"Hello", "World"};
        System.out.println(Arrays.toString(names));

        // multi dimensional array (Printing a grid)
        int[][] coordinates = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(coordinates[i][j] + " ");
            }
            System.out.println();
        }
    }
}
