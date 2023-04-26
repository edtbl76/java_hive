import java.io.File;
import java.util.Objects;

public class ListDirsEx {

    public static void main(String[] args) {
        File file;
        String[] paths;

        try {
            // create a new file
            file = new File("/tmp");

            //dump list of files + dirs into array
            paths = file.list();

            // print what is in the array
            // (Updated this to make sure that paths actually has something)
            for(String path : Objects.requireNonNull(paths)) { System.out.println(path); }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
