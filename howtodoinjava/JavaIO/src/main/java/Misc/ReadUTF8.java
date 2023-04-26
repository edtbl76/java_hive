package Misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadUTF8 {
    public static void main(String[] args) {

        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File("test_utf8"))))) {

            String str;
            while((str = input.readLine()) != null)
                System.out.println(str);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
