package CollectionsInJava.ArrayList.Examples;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class Serialization {

    public static void main(String[] args) {
        ArrayList<String> wonderTwins = new ArrayList<>(Arrays.asList("Zan", "Jayna", "Gleek"));

        try (FileOutputStream fos = new FileOutputStream("listData");
             ObjectOutputStream oos = new ObjectOutputStream(fos))  {
            oos.writeObject(wonderTwins);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
