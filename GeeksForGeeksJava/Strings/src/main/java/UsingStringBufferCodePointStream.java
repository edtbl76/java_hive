import java.util.stream.IntStream;

public class UsingStringBufferCodePointStream {

    public static void main(String[] args) {
        StringBuilder s = new StringBuilder(100_000);
        for (int i = 1; i < 65_536; i++) {
            s.appendCodePoint(i);
        }

        s.codePoints().forEach(cp -> System.out.println("CodePoint: " + cp));
    }

}
