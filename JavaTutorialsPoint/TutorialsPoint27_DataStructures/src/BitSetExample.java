import java.util.BitSet;
import java.util.stream.IntStream;

public class BitSetExample {

    public static void main(String[] args) {
        BitSet bits1 = new BitSet(16);
        BitSet bits2 = new BitSet(16);

        // set some bits
        IntStream.range(0, 16).forEach(
                n-> {
                    if((n % 2) == 0 ) bits1.set(n);
                    if((n % 5) != 0 ) bits2.set(n);
                }
        );

        System.out.println("Initial patterns in bits1: " + bits1);
        System.out.println("Initial patterns in bits2: " + bits2);

        // AND 'em
        bits2.and(bits1);
        System.out.println("\nbits2 AND bits1: " + bits2);

        // OR'em
        bits2.or(bits1);
        System.out.println("\nbits2 OR bits1: " + bits2);

        // XOR'em
        bits2.xor(bits1);
        System.out.println("\nbits2 XOR bits1: " + bits2);

    }
}
