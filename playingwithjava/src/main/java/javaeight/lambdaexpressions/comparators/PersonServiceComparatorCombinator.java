package javaeight.lambdaexpressions.comparators;

import utils.Generated;

import java.util.List;

import static java.util.Comparator.*;

@Generated
public class PersonServiceComparatorCombinator {

    private PersonServiceComparatorCombinator() {}

    public static List<Person> getPersons(List<Person> persons) {
        persons.sort(comparing(Person::getName));
        return persons;
    }

}
