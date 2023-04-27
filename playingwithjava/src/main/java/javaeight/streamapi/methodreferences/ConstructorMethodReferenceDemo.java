package javaeight.streamapi.methodreferences;

import javaeight.streamapi.methodreferences.utils.Person;
import utils.Generated;

import java.util.List;

@Generated
@SuppressWarnings("java:S106")
public class ConstructorMethodReferenceDemo {

    @SuppressWarnings({"Convert2MethodRef", "java:S1612"})
    public static void main(String[] args) {
        List<String> family = List.of("Edward", "Vanessa", "Michael", "Connor", "Gravy");

        // Code w/o constructor reference
        family
                .stream()
                .map(s -> new Person(s))
                .forEach(System.out::println);

        // Code w/ constructor reference
        family
                .stream()
                .map(Person::new)
                .forEach(System.out::println);
    }

}
