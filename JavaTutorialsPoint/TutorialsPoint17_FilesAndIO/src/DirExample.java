import java.io.File;

public class DirExample {

    public static void main(String[] args) {
        String dirname = "/tmp/user/java/example";
        File dir = new File(dirname);

        //noinspection ResultOfMethodCallIgnored
        dir.mkdirs();
    }
}
