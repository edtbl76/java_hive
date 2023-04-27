package utils;

public class Utils {

    @Generated
    private Utils() {
        throw new IllegalStateException("Utility Class");
    }

    public static String leftTrim(String string) {
        int pointer = 0;
        while (pointer < string.length() && Character.isWhitespace(string.charAt(pointer))) {
            pointer++;
        }

        return string.substring(pointer);
    }

    public static String rightTrim(String string) {
        int pointer = string.length() - 1;
        while (pointer >= 0 && Character.isWhitespace(string.charAt(pointer))) {
            pointer --;
        }
        return string.substring(0, pointer + 1);
    }

    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static double calculateAmdahlsLaw(int threads, double parallelizable) {
        double serializable = 1 - parallelizable;
        double parallelToThreads = parallelizable/threads;
        return 1 / (serializable + parallelToThreads);

    }

    public static double calculateUtilization(int threads, double parallelizable) {
        double speedup = calculateAmdahlsLaw(threads, parallelizable);
        return speedup / threads;
    }


    public static int summation(int number) {
        int sum = 0;
        for (int i = 0; i <= number; i++) {
            sum += i;
        }
        return sum;
    }

}
