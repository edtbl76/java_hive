import java.io.File;

public class FileClassEx {

    public static void main(String[] args) {

        File f;
        String[] strs = {"fileClass1.txt", "fileClass2.txt"};

        try {

            // Iterate through strings in array
            for( String s : strs) {
                // create new file
                f = new File(s);

                // print absolute path and whether or not it is executable
                System.out.println(f.getAbsolutePath() + " is executable: " + f.canExecute());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
