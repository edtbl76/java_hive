package Conversions;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class InputStreamToString_ApacheCommons {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*
            Apache Commons code is typically the most readable.
         */
        // Method 1 IOUTILS.cpy()
        StringWriter sw = new StringWriter();
        IOUtils.copy(new FileInputStream("pom.xml"), sw, "UTF-8");
        System.out.println(sw.toString());

        // Method 2 IOUtils.toString()   <------ MY PREFERENCE.
        System.out.println(
                IOUtils.toString(new FileInputStream("pom.xml"), StandardCharsets.UTF_8)
        );

    }
}
