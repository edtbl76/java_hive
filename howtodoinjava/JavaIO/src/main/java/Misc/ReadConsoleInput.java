package Misc;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadConsoleInput {

    public static void main(String[] args) {
        System.out.println("BUFFEREDREADER");
        usingBufferedReader();

        System.out.println("SCANNER");
        usingScanner();

    }

    private static void usingBufferedReader() {
        System.out.println("Name: ");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            System.out.println("You entered: " + input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void usingScanner() {
        System.out.println("Name: ");
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("You entered: " + in.nextLine());
        }
    }
}
