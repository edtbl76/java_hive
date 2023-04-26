package com.strings.StringConversion;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StringToInputStreamExample {


    public static void main(String[] args) {
        /*
            This is a common task in Java
            - String -> InputStream
         */
        String hw = "Hello world!";

        // EXAMPLE 1 using ByteArrayInputStream()
        InputStream stream = new ByteArrayInputStream(hw.getBytes());
        System.out.println(stream);     // This is going to print out java.io.ByteArrayInputStream@<address>

        // EXAMPLE 2 using Apache Commons IOUtils
        InputStream commonsStream = IOUtils.toInputStream(hw);
        System.out.println(commonsStream);  // This is also going to print out java.io.ByteArrayInputStream@<address>
    }
}
