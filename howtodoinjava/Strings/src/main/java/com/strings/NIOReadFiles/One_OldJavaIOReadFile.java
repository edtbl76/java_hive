package com.strings.NIOReadFiles;

import java.io.*;

public class One_OldJavaIOReadFile {

    public static void main(String[] args) {

        // declare buffered reader and a string in proper scope.
        BufferedReader bufferedReader = null;
        String currentLine = null;
        try {
            // pass the file to a FileReader, pass that to the heap allocated bufferedReader
            bufferedReader = new BufferedReader(new FileReader("./filetest"));

            // read each line until we run out of lines.
            while ((currentLine = bufferedReader.readLine()) != null) {
                System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // handle graceful closure of the bufferedReader.
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
