package collections.hashmap.identityhashmap;

import utils.Generated;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

@Generated
@SuppressWarnings("java:S106")
public class DemoIDHashMap {

    /*
        IdentityHashMap
        - impls Map, Serializable, Cloneable
        - extends AbstractMap

        - USE
            - evaluates equality of keys based on reference instead of equals().
            - this is an INTENTIONAL violation of a Map's general contract which
            mandates the use of equals() when comparing methods.

        - elements stored in random order
        - supports a single null key
        - not thread safe


        Differences from HashMap
        - IHM uses reference equality, HashMap uses object equality
        - IHM uses System.identityHashCode() rather than hashCode()
        - IHM doesn't require immutable keys (because it doesn't use equals()/hashCode())
        - has default capacity of 32
     */
    public static void main(String[] args) {

        Employee employee1 = new Employee(1, "Joe");
        Employee employee2 = new Employee(1, "Joe");

        // HashMap - only stores the last value, because they are considered equal
        Map<Employee, String> hashMap = new HashMap<>();
        hashMap.put(employee1, "employee1");
        hashMap.put(employee2, "employee2");
        System.out.println("HashMap employees: " + hashMap);
        System.out.println();

        // IdentityHashMap - stores both objects!
        Map<Employee, String> identityHashMap = new IdentityHashMap<>();
        identityHashMap.put(employee1, "employee1");
        identityHashMap.put(employee2, "employee2");
        System.out.println("IdentityHashMap employees: " + identityHashMap);
        System.out.println();


    }
}
