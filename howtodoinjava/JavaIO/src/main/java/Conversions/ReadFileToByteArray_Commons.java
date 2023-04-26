package Conversions;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ReadFileToByteArray_Commons {

    public static void main(String[] args) throws IOException  {

        // FileUtils version
        byte[] data = FileUtils.readFileToByteArray(new File("pom.xml"));
        System.out.println(Arrays.toString(data));

        data = IOUtils.toByteArray(new ByteArrayInputStream("sample".getBytes()));
        System.out.println(Arrays.toString(data));
    }
}
