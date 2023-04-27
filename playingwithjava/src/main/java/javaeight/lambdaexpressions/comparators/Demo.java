package javaeight.lambdaexpressions.comparators;

import utils.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
@SuppressWarnings("java:S106")
public class Demo {

    @SuppressWarnings("DataFlowIssue")
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>(List.of(
                new Person("Ed", 46, "USA"),
                new Person("Dorian", 46, "Australia"),
                new Person("Mehmed", 52, "Turkey"),
                new Person("Santosh", 27, "India"),
                new Person("Heather", 40, "China")
        ));

        // Anonymous Class call
        List<Person> sortedPersons = PersonServiceAC.getPersons(persons);
        sortedPersons.forEach(System.out::println);

        // Lambda call
        sortedPersons = PersonServiceLambda.getPersons(persons);
        sortedPersons.forEach(System.out::println);

        // Comparator Combinator
        sortedPersons = PersonServiceComparatorCombinator.getPersons(persons);
        sortedPersons.forEach(System.out::println);


    }
}
