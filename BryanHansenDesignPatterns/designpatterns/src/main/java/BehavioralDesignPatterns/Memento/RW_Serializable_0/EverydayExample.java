package BehavioralDesignPatterns.Memento.RW_Serializable_0;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EverydayExample {

    private static Employee deserialize() {
        Employee employee = null;
        try (FileInputStream fileInputStream = new FileInputStream("/tmp/employee.ser");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream))
        {
            employee = (Employee)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employee;
    }

    private static void serialize(Employee employee) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("/tmp/employee.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream))
        {
            objectOutputStream.writeObject(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("Jenny");
        employee.setAddress("MA");
        employee.setPhone("867-5309");

        serialize(employee);

        Employee deserializedEmployee = deserialize();

        System.out.println(deserializedEmployee);
    }
}
