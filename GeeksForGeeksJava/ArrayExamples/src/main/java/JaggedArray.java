public class JaggedArray {

    public static void main(String[] args) {

        int[][] jaggedOne = {
                new int[]{10, 20, 30, 40},
                new int[]{50, 60, 70, 80, 90, 100},
                new int[]{110, 120}
        };

        /*
            Displaying it isn't all that fancy.
            It's the same logic as we use for evenly distributed multi-dimensional arrays, but not all of the
            iterations are equal.
         */
        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < jaggedOne.length; i++) {
            for (int j = 0; j < jaggedOne[i].length; j++) {
                System.out.print(jaggedOne[i][j] + " ");
            }
            System.out.println();
        }
    }
}
