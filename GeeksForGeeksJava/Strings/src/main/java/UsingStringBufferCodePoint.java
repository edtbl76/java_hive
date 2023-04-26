import java.util.stream.IntStream;

public class UsingStringBufferCodePoint {

    public static void main(String[] args) {

        /*
            Demonstrates the use of unicode Code Points w/ StringBuffer
         */

        long startTime = System.currentTimeMillis();
        StringBuffer s = new StringBuffer(100_000);
        for (int i = 1; i < 40_000; i++) {
            s.appendCodePoint(i).append(" ");
            if (i % 80 == 0){
                s.append("\n");
            }
        }
        System.out.println(s);
        long endTime = System.currentTimeMillis();

        // Elapsed Time
        System.out.println("Elapsed Time :: " + (endTime - startTime) + " ms");

        // Get the code point at a specific index
        System.out.println(s.codePointAt(100));

        // Before a specific index
        System.out.println(s.codePointBefore(200));

        // Gets the number of code points from index to index.
        // (I selected the entire string)
        System.out.println(s.codePointCount(0, s.length() - 1));


    }
}
