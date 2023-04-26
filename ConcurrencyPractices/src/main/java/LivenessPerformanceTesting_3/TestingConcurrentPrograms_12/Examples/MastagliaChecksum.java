package LivenessPerformanceTesting_3.TestingConcurrentPrograms_12.Examples;

public class MastagliaChecksum {

    static int xorShift(int y) {
        System.out.println("Input: " + y);

        System.out.println("y << 6: " + (y << 6));
        y ^= (y << 6);
        System.out.println("y XOR y << 6: " + y);

        System.out.println("y >>> 21: " + (y >>> 21));
        y ^= (y >>> 21);
        System.out.println("y XOR y >>> 21 " + y);

        System.out.println("y << 7: " + (y << 7));
        y ^= (y << 7);
        System.out.println("y XOR v << 7: " + y);

        return y;
    }

    public static void main(String[] args) {
        xorShift(1);
    }
}
