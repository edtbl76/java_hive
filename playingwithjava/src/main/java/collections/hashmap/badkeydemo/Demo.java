package collections.hashmap.badkeydemo;

import utils.Generated;

import java.util.HashMap;
import java.util.Map;

@Generated
@SuppressWarnings({"java:S106", "DuplicatedCode"})
public class Demo {

    public static void main(String[] args) {
        Employee employee1 = new Employee(123, "Jane");
        Employee employee2 = new Employee(123, "Jane");

        Map<Employee, Integer> map = new HashMap<>(Map.of(employee1, 56, employee2, 45));

        for(Map.Entry<Employee, Integer> entry : map.entrySet()) {
            System.out.println("ID: " + entry.getKey().getId() + " Name: " + entry.getKey().getName());
        }
    }
}
