package com.java8.DirectoryIterate;

import java.io.File;
import java.util.Arrays;

public class HiddenFilesExample {

    public static void main(String[] args) {
        final File[] files = new File("/Users/emangini/").listFiles(File::isHidden);
        System.out.println(Arrays.toString(files));
    }
}
