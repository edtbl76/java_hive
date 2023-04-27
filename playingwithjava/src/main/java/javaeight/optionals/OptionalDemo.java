package javaeight.optionals;

import javaeight.optionals.utils.Person;
import utils.Generated;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Generated
@SuppressWarnings("java:S106")
public class OptionalDemo {

    Map<Integer, Person>  personMap = new HashMap<>();

    public Optional<Person> getPerson(Integer id) {
        return Optional.ofNullable(personMap.get(id));
    }

    public static void main(String[] args) {
        OptionalDemo demo = new OptionalDemo();

        Optional<Person> person = demo.getPerson(1);

        // Before getting the value, we can use ifPresent() to check for null
        String result = person.isPresent()
                ? person.get().getName()
                : "No person returned";
        System.out.print(result);

    }
}
