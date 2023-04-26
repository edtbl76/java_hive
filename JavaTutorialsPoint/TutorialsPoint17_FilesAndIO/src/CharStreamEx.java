import java.io.*;

// (Turn off dupes, because this code shows up in ByteStreamEx)
@SuppressWarnings("Duplicates")
public class CharStreamEx {

    public static void main(String[] args)
            throws IOException
    {

        try (
                // Auto Resource Management (Java 7) in try blocks. (No finally required)
                FileReader in = new FileReader("input.txt");
                FileWriter out = new FileWriter("output.txt")
        )
        {

            int c;
            while ((c = in.read()) != -1) {
                String binString = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
                System.out.println("Input Byte: " + c +
                        "\t\tBinary: " + binString +
                        "\t\tByteChar (ASCII): " + (char) c);
                out.write(c);
            }
        }
    }
}
