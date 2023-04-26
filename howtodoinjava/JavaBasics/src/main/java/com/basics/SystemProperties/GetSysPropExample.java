package com.basics.SystemProperties;

import java.util.Properties;

public class GetSysPropExample {

    public static void main(String[] args) {
        // List All System properties
        Properties props = System.getProperties();
        props.list(System.out);

        // Get a particular System property given its key
        // Returns value or null
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("java.library.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
