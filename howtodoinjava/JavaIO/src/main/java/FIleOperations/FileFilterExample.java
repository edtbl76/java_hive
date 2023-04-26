package FIleOperations;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileFilterExample {
    public static void main(String[] args) {
        FileFilterExample ffe = new FileFilterExample();
        ffe.getFiles("./");
    }

    public void getFiles(String dir) {
        File directory = new File(dir);
        if (!directory.exists()) {
            System.out.println(String.format("Directory %s doesn't exist", dir));
            return;
        }

        if (!directory.isDirectory()) {
            System.out.println(String.format("%s isn't a directory", dir));
            return;
        }

        List<File> files = Arrays.asList(Objects.requireNonNull(directory.listFiles(xmlFilter)));
        files.forEach(file -> System.out.println(file.getName()));
    }

    private FileFilter xmlFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith("xml");
        }
    };
}
