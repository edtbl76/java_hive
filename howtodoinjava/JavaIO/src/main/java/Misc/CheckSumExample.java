package Misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.IntStream;

public class CheckSumExample {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // UGLY
        System.out.println(
                getFileChecksum(
                        MessageDigest.getInstance("MD5"),
                        new File("pom.xml")
                )
        );

        // PRETTIER
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        File file = new File("pom.xml");
        System.out.println(getFileChecksum(md5, file));
    }

    private static String getFileChecksum(MessageDigest digest, File file)
            throws IOException {

        try (FileInputStream fis = new FileInputStream(file)) {

            // byte array to read data in chunks
            byte[] byteArr = new byte[1024];
            int bytesCount = 0;

            // read file data (in chunks!) and update in message digest.
            while ((bytesCount = fis.read(byteArr)) != -1) {
                digest.update(byteArr, 0, bytesCount);
            }

            // gets the bytes from the hash.
            byte[] bytes = digest.digest();

            // convert to decimal
            StringBuilder sb = new StringBuilder();
            IntStream.range(0, bytes.length).forEach(
                    b -> sb.append(Integer.toString((bytes[b] & 0xff) + 0x100, 16).substring(1))
            );
            return sb.toString();
        }
    }
}
