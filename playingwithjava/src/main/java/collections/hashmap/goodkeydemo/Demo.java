package collections.hashmap.goodkeydemo;

import utils.Generated;

import java.util.HashMap;
import java.util.Map;

/*
    RULE OF THUMB:
    - when using custom objects as a HM Key, the class should be
    - Immutable
        OR
    - the fields that are used to calculate the hashcode should be made final.
 */
@Generated
@SuppressWarnings({"java:S106", "java:S1854", "DuplicatedCode"})
public class Demo {

    public static void main(String[] args) {
        Employee employee1 = new Employee(123, "Jane");
        Employee employee2 = new Employee(123, "Jane");
        Map<Employee, Integer> map;

        // Throws an IAE because we are trying to store a duplicate key
        try {
            //noinspection UnusedAssignment
            map = new HashMap<>(Map.of(employee1, 56, employee2, 45));
        } catch (IllegalArgumentException ignored) {
           // does nothing
        }

        map = new HashMap<>(Map.of(employee1, 56));
        for(Map.Entry<Employee, Integer> entry : map.entrySet()) {
            System.out.println("ID: " + entry.getKey().getId() + " Name: " + entry.getKey().getName());
        }

        /*
            This returns null...
            The reason is that since we changed the employee object, the hashcode also changes.
            - however, the new hashcode was never stored, and the get() query is searching for the
            new hash.
            - the OLD hashcode is still stored/
         */
        employee1.setName("Joey");
        System.out.println(map.get(employee1));

        // Changing the name back will result in the creation of the same hashcode (one of the
        // basic principles of a good hash), so we'll be able to retrieve the value.
        employee1.setName("Jane");
        System.out.println(map.get(employee1));



    }

}
