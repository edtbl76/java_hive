import java.io.*;

@SuppressWarnings("Duplicates")
public class ByteStreamEx {

    public static void main(String[] args)
            throws IOException
    {

        try (
                // Auto Resource Management (Java 7) in try blocks. (No finally required)
                FileInputStream in = new FileInputStream("input.txt");
                FileOutputStream out = new FileOutputStream("output.txt")
        )
        {

            int c;
            while ((c = in.read()) != -1) {

                /* This is just a fun little pain in the butt.

                    In order to demonstrate the Byte Stream, I wanted to show the binary words of each ASCII char.
                    toBinaryString() doesn't support padding 0's, so the shortcut is to
                    - truncate to a length of 8
                    - leftpad w/ spaces.
                    - then we add a .replace() on the result, swapping out the left padded spaces w/ a 0.
                */
                String binString = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');


                /*
                I did this on purpose, so that we can crack open the ByteStream.

                int c is the ASCII numeric code for each character being sent from Input to Output.

                That means this file is being read/written 1 byte at a time.
                 */
                System.out.println("Input Byte: " + c +
                        "\t\tBinary: " + binString +
                        "\t\tByteChar (ASCII): " + (char) c);
                out.write(c);
            }
        }

    }
}
