package javaeight.optionals;

import javaeight.optionals.utils.Person;
import utils.Generated;

import java.util.HashMap;
import java.util.Map;

@Generated
@SuppressWarnings({"java:S106", "java:S1854", "unused"})
public class NullAbsentValueDemo {

    Map<Integer, Person> personMap = new HashMap<>();
    public Person getPerson(Integer id) {
        return personMap.get(id);
    }
    public static void main(String[] args) {

        NullAbsentValueDemo demo = new NullAbsentValueDemo();

        Person person = demo.getPerson(1);
        // This returns null!!!!
        System.out.println(person);

    }
}
