package StructuralDesignPatterns.Decorator.RW_File_0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class EverydayDemo {

    public static void main(String[] args) {

        /*
            OutputStream doesn't know about Files and Data. It's just OUTPUT
            - the FileOutputStream is a Decorator that knows about writing... FILES
            - the DataOutputStream is a Decorator that knows about writing... DATA
         */
        File file = new File("./output.txt");
        try (OutputStream outputStream = new FileOutputStream(file);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream)
        ) {
            dataOutputStream.writeChars("text");
            dataOutputStream.flush();

            // Read it back.
            InputStream inputStream = new FileInputStream(file);
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            while(dataInputStream.available() > 0) {
                System.out.print(dataInputStream.readChar());
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
