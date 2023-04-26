public class PassingArraysToMethods {

    private static void printArray(int[] array) {
        for(int element  : array ) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Declare it first
        int[] a = {1, 2, 3, 4, 5};
        printArray(a);

        // One-Liner
        printArray(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1});
    }
}
