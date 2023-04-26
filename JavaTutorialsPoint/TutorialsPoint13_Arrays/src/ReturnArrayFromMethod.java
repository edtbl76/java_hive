public class ReturnArrayFromMethod {

    private static int[] reverse(int[] list)  {
        int[] result = new int[list.length];

        for (int i = 0, j = result.length - 1; i < list.length; i++, j--) {
            result[j] = list[i];
        }
        return result;
    }

    private static void printArray(int[] list) {
        for (int element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] forward = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        printArray(forward);
        printArray(reverse(forward));
    }
}
