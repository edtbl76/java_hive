package javaeight.lambdaexpressions.comparators;

import utils.Generated;

import java.util.List;

@Generated
public class PersonServiceLambda {

    private PersonServiceLambda() {}

    @SuppressWarnings("ComparatorCombinators")
    public static List<Person> getPersons(List<Person> persons) {
        persons.sort((person1, person2) -> person1.getName().compareTo(person2.getName()));
        return persons;
    }

}
