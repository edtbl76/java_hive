package Misc;

import java.io.*;

public class WriteUTF8 {

    public static void main(String[] args) {

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(new File("test_utf8"))
                )
        )) {
            writer.append("Line").append("\n");
            writer.append("UTF8!\n");
            writer.append("क्षेत्रफल = लंबाई * चौड़ाई").append("\n");
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
