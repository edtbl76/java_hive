package com.java7.changes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesExample {

    /*
        Before Java 7
        - finally blocks were requires to cleanup resources to prevent
        system corruption

        - new interface AutoCloseable is invoked by JVM as soon as a try block finishes.
        (You con't have to call close() in your code, and in fact you should avoid this to prevent
        unexpected results)

        AUTOMATIC RESOURCE CLEANUP
            - using try-with-resources block replaces the functionality of the finally block
     */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("./pom.xml"))) {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
