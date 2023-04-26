import java.io.*;

class Employee implements java.io.Serializable {
    String name;
    String address;
    transient int SSN;  // 'transient' modifier is used to exclude a field during serialization.
                        // this means that the SSN field is initialized to 'default value'
                        // (0/false for primitives and 'null' for reference types)
                        // upon deserialization.
    int number;

    @SuppressWarnings("unused")
    public void mailCheck() {
        System.out.println("Mailing a check to " + name + " " + address);
    }
}

public class SerializationDemo {

    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.name = "Cassius Clay";
        employee.address = "Baltimore, MD";
        employee.SSN = 999999999;
        employee.number = 1;

        // Serialize it
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("employee.ser");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ){
            objectOutputStream.writeObject(employee);
            System.out.println("Serialized data is saved in employee.ser");

        } catch (IOException e) {
            e.printStackTrace();
        }

        // DeSerialize
        Employee employeeDeserialized;

        // Remember the auto resource management in Java 7? don't have to do all of the close statements.
        try(
                FileInputStream fileInputStream = new FileInputStream("employee.ser");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ){
            // cast the response to desired type, because it returns a generic object.
            employeeDeserialized = (Employee)objectInputStream.readObject();

        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Deserialized Employee: ");
        System.out.println("\tName: " + employeeDeserialized.name);
        System.out.println("\tAddress: " + employeeDeserialized.address);
        System.out.println("\tSSN: " + employeeDeserialized.SSN);
        System.out.println("\tNumber: " + employeeDeserialized.number);
    }
}
