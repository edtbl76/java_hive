import java.io.*;

public class InputOutputStreamsEx {

    public static void main(String[] args)
            throws IOException
    {

        try (
                // Java 7 introduced Auto Resource Management in try blocks.
                OutputStream os = new FileOutputStream("test.txt");
                InputStream is = new FileInputStream("test.txt")
        )
        {
            byte[] bArray = {33,115,112,48,97}; // The examples I had were all invalid characters this is '!sp0a'

            // write the bytes out
            System.out.print("Writing: ");
            for ( byte b : bArray) {
                System.out.print("..." + b + "(" + (char)b + ")");
                os.write(b);
            }
            System.out.println();

            // Read the bytes, and measure available
            while (is.available() > 0) {
                // pull the data out of the file (Remember that read() returns the value, so we can only do it once
                // per loop.
                int data = is.read();

                // This is why we store the value from is.read() in an int, so we can convert it to validate
                // that what we wrote to the file is what we are reading back.
                System.out.print("Available Bytes: " + is.available() +
                        " Byte: " + data + "(" + (char)data + ")\n"
                );
            }


        }
    }
}
