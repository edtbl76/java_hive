package javaeight.lambdaexpressions.comparators;

import utils.Generated;

import java.util.Comparator;
import java.util.List;

@Generated
public class PersonServiceAC {

    private PersonServiceAC() {}

    @SuppressWarnings({"Convert2Lambda", "java:S1604"})
    public static List<Person> getPersons(List<Person> persons) {
        persons.sort(new Comparator<>() {
            @Override
            public int compare(Person person1, Person person2) {
                return person1.getName().compareTo(person2.getName());
            }
        });
        return persons;
    }

}
