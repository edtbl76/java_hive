package Conversions;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.Charset;

public class StringToInputStream_Commons {

    /*
        Apache Commons is nice because it makes code readable.
     */
    public static void main(String[] args) {
        String sample = "I am a string. I identify as an input stream.";

        /*
        NOTE; Using toInputStream() w/o the charset is DEPRECATED.
        - it is recommended to provide both the String and target CHARACTER SET.
         */
        InputStream stream = IOUtils.toInputStream(sample, Charset.defaultCharset());

        System.out.println("String: [" + sample + "]");
        System.out.println("Stream: [" + stream + "]");

    }
}
