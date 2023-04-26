package CollectionsInJava.ArrayList.Examples;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Serialization2_SerializableInterface {

    public static void main(String[] args) throws Exception {
        ArrayList<Employee> emps = new ArrayList<>();

        emps.add(new Employee("1", "Ed", "Mangini"));
        emps.add(new Employee("2", "Vanessa", "Mangini"));

        try (FileOutputStream fos = new FileOutputStream("employeeData");
             ObjectOutputStream oos = new ObjectOutputStream(fos)){

            // (If the Employee class wasn't serializable... this would throw a NotSerialized exception)
            oos.writeObject(emps);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class Employee implements Serializable {

    private String id;
    private String firstName;
    private String lastName;

    public Employee(String id, String firstName, String lastName) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
