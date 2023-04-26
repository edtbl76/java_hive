package Conversions;

import com.google.common.io.CharStreams;

import java.io.*;

public class InputStreamToString_GuavaCharStreams {

    public static void main(String[] args)  throws IOException {

        InputStream in = new FileInputStream("pom.xml");
        String text = null;
        try (Reader reader = new InputStreamReader(in)) {
            text = CharStreams.toString(reader);
        }
        System.out.println(text);
    }
}
