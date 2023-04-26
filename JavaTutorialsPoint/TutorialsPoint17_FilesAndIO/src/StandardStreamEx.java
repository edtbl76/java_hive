import java.io.*;

public class StandardStreamEx {

    public static void main(String[] args)
            throws IOException
    {
        try (
                InputStreamReader cin = new InputStreamReader(System.in)
        )
        {
            System.out.println("Enter characters, 'q' to quit.");

            char c;
            do {
                c = (char) cin.read();
                System.out.println(c);
            } while (c != 'q');
        }
    }
}
