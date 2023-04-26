package com.strings.StringConversion;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
import com.google.common.io.CharStreams;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Scanner;

public class InputStreamToStringExample {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        //
        // EXAMPLE 1: Using Google Guava's ByteSource
        //

        // start by setting up an input stream and handling the possible exception.
        final InputStream inputStream = new FileInputStream(new File("./filetest"));

        ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() throws IOException {
                return inputStream;
            }
        };

        String bsText = byteSource.asCharSource(Charsets.UTF_8).read();
        System.out.println("ByteSource: [" + bsText + "]");

        //
        // EXAMPLE 2: Using Google Guava's CharStreams
        //
        final InputStream is2 = new FileInputStream(new File("./filetest"));
        String csText = null;

        try (final Reader reader = new InputStreamReader(is2)) {
            csText = CharStreams.toString(reader);
        }
        System.out.println("CharStreams: [" + csText + "]");

        //
        // EXAMPLE 3: Using a BufferedReader.   (Most Popular... but I think this is probably the ugliest)
        //
        InputStream is3 = new FileInputStream(new File("./filetest"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is3));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        System.out.println("BufferedReader: [" + out.toString() + "]");
        reader.close();

        //
        // EXAMPLE 4: Apache Commons IOUtils.copy()    (Most Readable... might be well worth the extra dependency!)
        //
        StringWriter writer = new StringWriter();
        IOUtils.copy(new FileInputStream(new File("./filetest")), writer, "UTF-8");
        String copyString = writer.toString();
        System.out.println("IOUtils.copy() [" + copyString + "]");

        //
        // EXAMPLE 5: Apache Commons IOUtils.toString() (Most Readable... might be well worth the extra dependency!)
        //
        String str = IOUtils.toString(new FileInputStream(new File("./filetest")), "UTF-8");
        System.out.println("IOUtils.toString() [" + str + "]");

        //
        // EXAMPLE 6: Using java.util.Scanner
        //
        FileInputStream fileInputStream = new FileInputStream(new File("./filetest"));
        Scanner scanner = new Scanner(fileInputStream, "UTF-8").useDelimiter("\\A");
        String scanStr = scanner.hasNext() ? scanner.next() : "";
        System.out.println("java.util.Scanner [" + scanStr + "]");
    }
}
