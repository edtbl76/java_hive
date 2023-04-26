package FIleOperations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileAttributeManagementExamples {
    public static void main(String[] args) throws IOException {
        markReadOnlyNatively();
        markReadOnlySetWritable();


        cleanUp();
    }

    private static void markReadOnlyNatively() throws IOException {
        File readOnlyFile = new File("testFile1");
        readOnlyFile.setReadOnly();

        if(readOnlyFile.exists())
            readOnlyFile.delete();
        readOnlyFile.createNewFile();
    }

    private static void markReadOnlySetWritable() throws IOException {
        File rof = new File("testFile2");

        if (rof.exists())
            rof.delete();
        rof.createNewFile();
        rof.setWritable(false);
    }

    private static void cleanUp() throws IOException {

        Files.walk(Paths.get(""))
                .map(Path::toFile)
                .filter(file -> file.toString().startsWith("testFile"))
                .forEach(file -> file.setWritable(true));
    }

}
