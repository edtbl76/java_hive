package FIleOperations;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileCopyExamples {

    public static void main(String[] args) throws IOException {
        copyCommonsFileUtils();
        copyCommonsIOUtils();
        copyNIOFilesCopy();
        copyNIOFileChannelTransfer();
        copyStreams();
    }

    // Apache Commons FileUtils
    private static void copyCommonsFileUtils() throws IOException {
        FileUtils.copyFile(
                new File("testFile1"),
                new File("testFileCommons1")
        );
    }

    // Apache Commons IOUtils
    private static void copyCommonsIOUtils() throws IOException {
        IOUtils.copy(
                new FileInputStream(new File("testFile2")),
                new FileOutputStream(new File("testFileCommons2"))
        );
    }

    // NIO Files.copy()
    private static void copyNIOFilesCopy() throws IOException {
        Files.copy(
                Paths.get("testFile3"),
                Paths.get("testFileNIOFilesCopy"),
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    // NIO FileChannel.transferTo()
    private static void copyNIOFileChannelTransfer() throws IOException {
        File toCopy = new File("testFile4");

        try (FileInputStream in = new FileInputStream(toCopy);
             FileOutputStream out = new FileOutputStream("testFileNIOFileChannelTransfer")) {

            in.getChannel().transferTo(0, toCopy.length(), out.getChannel());
        }
    }

    // Streams
    private static void copyStreams() throws IOException {

        try (FileInputStream fis = new FileInputStream("testFile4");
             FileOutputStream fos = new FileOutputStream("testFileStreams")) {

            byte[] buffah = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffah)) > 0)
                fos.write(buffah, 0, bytesRead);
        }
    }
}
