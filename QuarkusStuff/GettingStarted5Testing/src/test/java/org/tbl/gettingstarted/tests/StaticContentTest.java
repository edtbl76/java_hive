package org.tbl.gettingstarted.tests;

import com.sun.xml.bind.v2.util.ByteArrayOutputStreamEx;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static java.nio.charset.StandardCharsets.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class StaticContentTest {

    @TestHTTPResource("index.html")
    URL url;

    @Test
    public void testIndexHtml() throws Exception {
        try (InputStream inputStream = url.openStream()) {
            String contents = readStream(inputStream);
            assertTrue(contents.contains("<title>Testing Quarkus</title>"));
        }
    }


    public static String readStream(InputStream inputStream) throws IOException {
        byte[] data = new byte[1024];
        int read;
        ByteArrayOutputStream out = new ByteArrayOutputStreamEx();
        while ((read = inputStream.read(data)) > 0) {
            out.write(data, 0 , read);
        }
        return out.toString(UTF_8);
    }
}
