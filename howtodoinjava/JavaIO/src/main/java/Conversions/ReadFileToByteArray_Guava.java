package Conversions;

import com.google.common.io.ByteStreams;
import com.google.common.io.Files;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ReadFileToByteArray_Guava {

    public static void main(String[] args) throws IOException {

        byte[] data = Files.toByteArray(new File("pom.xml"));
        System.out.println(Arrays.toString(data));

        byte[] stream = ByteStreams.toByteArray(new ByteArrayInputStream("sample".getBytes()));
        System.out.println(Arrays.toString(stream));
    }

}
