import java.io.*;

public class FileReaderWriterEx {

    public static void main(String[] args)
            throws IOException
    {
        // Create a file obj
        File file = new File("FileReaderExample1.txt");

        // Create a FileWriter (so we have something to read from !)
        FileWriter fw = new FileWriter(file);

        // Write something to file
        fw.write("This\n is\n an\n example\n");
        fw.flush();  // empties the buffah
        fw.close();  // cleans up!

        // Create a FileReader
        FileReader fr = new FileReader(file);
        char[] a = new char[50];
        //noinspection ResultOfMethodCallIgnored
        fr.read(a);  // reads contents to the array I created.

        for(char c : a)
            System.out.print(c);  // prints characters one at a time
        fr.close();
        System.out.println();
    }
}
