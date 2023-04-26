import java.io.*;

public class MultiCatchExample {

    public static void main(String[] args) {
        File file = new File("MultiCatchExample.txt");

        try {
            FileInputStream in = new FileInputStream(file);
            // deliberately unused to demonstrate multiple catch blocks.
            @SuppressWarnings("unused") byte x = (byte) in.read();
        } catch (FileNotFoundException f) {
            System.out.println("Different Exception");
            f.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}
