/*
Java program that demonstrates the inner workings of a Class type object
created by the JVM to represent a .class file in memory.
 */

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class JavaClassExample {
    public static void main(String[] args) {

        // Create a pet
        Pet pet = new Pet();

        // Get the class object created by the JVM and print it out
        Class c1 = pet.getClass();
        System.out.println("ClassName: " + c1.getName());

        // Get all of the methods
        Method methods[] = c1.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("MethodName: " + method.getName());
        }

        Field fields[] = c1.getDeclaredFields();
        for (Field field: fields) {
            System.out.println("FieldName: " + field);
        }




    }
}


// Sample Class
class Pet {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
