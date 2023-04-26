public class Array3DTable {

    public static void main(String[] args) {

        int[][][] table = {
                {{ 1, 2 }, { 3, 4 }},
                {{ 5, 6 }, { 7, 8 }},
                {{ 9, 0 }, { 1, 2 }}
        };

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                for (int k = 0; k < table[i][j].length; k++) {
                    System.out.print(table[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
