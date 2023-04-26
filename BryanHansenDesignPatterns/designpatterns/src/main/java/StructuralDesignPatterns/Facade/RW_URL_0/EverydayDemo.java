package StructuralDesignPatterns.Facade.RW_URL_0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class EverydayDemo {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https", "www.pluralsight.com", 443, "/authors/bryan-hansen");

        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
    }
}
