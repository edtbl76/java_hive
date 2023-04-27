package javaeight.optionals;

import javaeight.optionals.utils.Person;
import utils.Generated;

import java.util.Optional;

@Generated
@SuppressWarnings({"unused", "DataFlowIssue"})
public class CreatingOptionalDemo {

    public static void main(String[] args) {

        // setup
        Person defaultPerson = new Person();
        Person person = new Person("Ed", 46);


        //1. empty() method, which contains null values.
        Optional<Person> empty = Optional.empty();

        //2. of() Method -- This throws an NPE if we create an Optional w/ null values
        Optional<Person> defaultOptional = Optional.of(defaultPerson);
        Optional<Person> optional = Optional.of(person);

        // 3. ofNullable() <- use when we aren't sure if the object is null or not
        Optional<Person> defaultNullable = Optional.ofNullable(defaultPerson);
        Optional<Person> nullable = Optional.ofNullable(person);

    }
}
