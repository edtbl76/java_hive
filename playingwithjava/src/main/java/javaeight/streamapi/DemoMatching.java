package javaeight.streamapi;

import javaeight.streamapi.utils.Person;
import utils.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
@SuppressWarnings("java:S106")
public class DemoMatching {

    /*
        matching
        - terminal operations that are used to check if elements w/ certain criteria are in the stream or not.
     */
    static List<Person> family = new ArrayList<>(List.of(
            new Person("Edward", 46),
            new Person("Vanessa", 41),
            new Person("Michael", 22),
            new Person("Connor", 11)
    ));




    public static void main(String[] args) {

        /*
            1. anyMatch(Predicate predicate)

                TRUE: if at least one element matches the predicate
                FALSE: if no elements match the criteria
                        OR
                       the stream is empty
         */
        boolean anyAdult = family.stream()
                .anyMatch(person -> isAdult(person.getAge()));
        System.out.println("Is there at least one adult?: " + anyAdult);


        /*
            2. allMatch(Predicate predicate)

                TRUE: if all elements matche the predicate
                FALSE : if some elements match the predicate OR empty stream
         */
        boolean allAdult = family.stream()
                .allMatch(person -> isAdult(person.getAge()));
        System.out.println("Does the entire family consist of adults? " + allAdult);


        /*
            3. noneMatch(Predicate predicate)

                TRUE: if no elements match the predicate or the stream is empty
                FALSE: if at least one element matches.

                "opposite" of anyMatch
         */
        boolean notSenior = family.stream()
                .noneMatch(person -> isSenior(person.getAge()));
        System.out.println("Are all family members pre-retirement age? " + notSenior);
    }

    public static boolean isAdult(Integer age) {
        return age >= 18;
    }

    public static boolean isSenior(Integer age) {
        return age >= 65;
    }
}
