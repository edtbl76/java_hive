import java.io.FileReader;
import java.io.IOException;

public class AutoResourceManagementEx {

    public static void main(String[] args) {

        // Purpose is to demonstrate the use of try-with-resources for auto resource management.
        // If the file had existed in order to be opened, it would have been automatically closed after the
        // try block... removing the need for a 'finally' block.
        try (FileReader fr = new FileReader("arm.txt")) {

            // Create char array
            char[] a = new char[50];

            //noinspection ResultOfMethodCallIgnored
            fr.read(a); // dumps file content into an array.

            for( char c : a ) { System.out.println(c); } // prints chars one at a time
        } catch (IOException e) {
            e.printStackTrace(); // <-- This is going to happen, because file doesn't exist.
        }
    }
}
