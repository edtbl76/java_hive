import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckedExceptionEx {

    public static void main(String[] args) {
        File file = new File("CheckedException.txt");

        try {
            // deliberately unused. We are demonstrating the output of an exception.
            @SuppressWarnings("unused") FileReader fr = new FileReader(file);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

    }
}
