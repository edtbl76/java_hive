import java.io.*;

public class ByteArrayStreamEx {

    public static void main(String[] args)
            throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(12);

        while (baos.size() != 10) {
            // Gets input from user. (Fake)
            baos.write("Hello".getBytes());
        }

        byte[] b = baos.toByteArray();
        System.out.println("Print whatcha got...");

        for ( byte element : b ) {
            // printing the characters.
            System.out.print((char)element  + "  ");
        }
        System.out.println("  ");

        int c;
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        System.out.println("Converting characters to upper case ");

        for(int y = 0; y < 1; y++) {
            while((c = bais.read())!= -1) {
                System.out.print(Character.toUpperCase((char)(c)));
            }
        }
        bais.reset();
        System.out.println();

    }
}
