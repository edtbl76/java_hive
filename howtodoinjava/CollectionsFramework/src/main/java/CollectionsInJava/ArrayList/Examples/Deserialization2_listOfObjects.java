package CollectionsInJava.ArrayList.Examples;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Deserialization2_listOfObjects {

    public static void main(String[] args) {
        ArrayList<Employee> emps;

        try (FileInputStream fis = new FileInputStream("employeeData");
             ObjectInputStream ois = new ObjectInputStream(fis)){
            emps = (ArrayList<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return;
        }

        emps.forEach(System.out::println);
    }
}
