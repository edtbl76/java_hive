package CollectionsInJava.ArrayList.Examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Deserialization {

    public static void main(String[] args) {
        ArrayList<String> namesList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream("listData");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            namesList = (ArrayList<String>) ois.readObject();
        } catch (IOException|ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        namesList.forEach(System.out::println);
    }
}
