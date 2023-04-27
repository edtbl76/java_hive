package javaeight.lambdaexpressions.predicate;

import utils.Generated;

import java.util.function.BiPredicate;

@SuppressWarnings({"SameParameterValue", "java:S106"})
@Generated
public class BiPredicateDemo {


    public static final String IS_ELIGIBLE = "Person is eligible: ";

    static boolean isPersonEligible(Person person, Integer age, BiPredicate<Person, Integer> predicate) {
        return predicate.test(person, age);
    }

    static boolean isPersonIneligible(Person person, Integer age, BiPredicate<Person, Integer> predicate) {
        return predicate.negate().test(person, age);
    }


    public static void main(String[] args) {

        // Setup
        Person sweetheart = new Person("Vanessa", 25);

        // BiPredicates
        BiPredicate<Person, Integer> greaterThanAge = (person, age) -> person.getAge() > age;
        BiPredicate<Person, Integer> lessThanAge = (person, age) -> person.getAge() < age;

        boolean eligible = isPersonEligible(sweetheart, 18, greaterThanAge);
        display(eligible);

        eligible = isPersonEligible(sweetheart, 25, greaterThanAge.and(lessThanAge));
        display(eligible);

        eligible = isPersonEligible(sweetheart, 18, greaterThanAge.or(lessThanAge));
        display(eligible);

        eligible = isPersonIneligible(sweetheart, 18, greaterThanAge);
        display(eligible);

    }

    private static void display(boolean eligible) {
        System.out.println(IS_ELIGIBLE + eligible);
    }
}
