import java.util.Enumeration;
import java.util.Vector;
import java.util.stream.IntStream;

public class EnumerationIterator {

    public static void main(String[] args) {

        // Create, Seed, Display old-school Vector
        Vector<Integer> vector = new Vector<>();
        IntStream.range(0, 10).forEach(vector::addElement);
        System.out.println(vector);

        /*
            Create Enumeration type object and iterate over it.
         */
        Enumeration<Integer> enumeration = vector.elements();
        while (enumeration.hasMoreElements()) {
            int element = enumeration.nextElement();
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
