package Conversions;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.google.common.io.CharStreams;

import java.io.*;

public class InputStreamToString_GuavaByteSource {

    public static void main(String[] args) throws Exception {
        InputStream in = new FileInputStream("pom.xml");

        // Option 1: ByteSource (My Preference, although not as readable)
        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() throws IOException {
                return in;
            }
        };

        String text = byteSource.asCharSource(Charsets.UTF_8).read();
        System.out.println(text);

    }
}
