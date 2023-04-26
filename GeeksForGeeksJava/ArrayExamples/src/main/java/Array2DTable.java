public class Array2DTable {

    public static void main(String[] args) {

        int[][] table = {{7, 8, 9}, {4, 5, 6}, {1, 2, 3}};

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }

    }
}
