import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class MultiCatchExample2 {

    public static void main(String[] args) {
        File file = new File("MultiCatchExample.txt");

        try {
            FileInputStream in = new FileInputStream(file);
            // deliberately unused to demonstrate multiple catch blocks.
            @SuppressWarnings("unused") byte x = (byte) in.read();
        } catch (IOException| NullPointerException f) {
            // if the exceptions aren't parent/childs of each other you can use this notation.
            f.printStackTrace();
        }
    }
}
