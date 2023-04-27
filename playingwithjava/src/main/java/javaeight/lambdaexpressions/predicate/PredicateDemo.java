package javaeight.lambdaexpressions.predicate;

import utils.Generated;

import java.util.function.Predicate;

@Generated

public class PredicateDemo {

    static boolean isPersonEligible(Person person, Predicate<Person> predicate) {
        return predicate.test(person);
    }

    static boolean isPersonIneligible(Person person, Predicate<Person> predicate) {
        return predicate.negate().test(person);
    }

    @SuppressWarnings({"java:S106", "java:S1192"})
    public static void main(String[] args) {
        Person person = new Person("Edward", 46);

        // Predicates
        Predicate<Person> greaterThanEighteen = p -> p.getAge() > 18;
        Predicate<Person> greaterThanTwentyFive = p -> p.getAge() > 25;
        Predicate<Person> lessThanSixty = p -> p.getAge() < 60;


        // predicate test()
        boolean eligible = isPersonEligible(person, greaterThanEighteen);
        System.out.println("Am I able to vote: " + eligible);

        // and()
        Predicate<Person> predicateAnd = greaterThanEighteen.and(lessThanSixty);
        eligible = isPersonEligible(person, predicateAnd);
        System.out.println("Person is > 18, < 60 " + eligible);

        // or()
        Predicate<Person> predicateOr = greaterThanEighteen.or(greaterThanTwentyFive);
        eligible = isPersonEligible(person, predicateOr);
        System.out.println("Person is > 18 or > 25 " + eligible);

        // predicate negate()
        eligible = isPersonIneligible(person, greaterThanEighteen);
        System.out.println("Am I able to vote: " + eligible);

        eligible = isPersonIneligible(person, predicateAnd);
        System.out.println("Person is > 18, < 60 " + eligible);

        eligible = isPersonIneligible(person, predicateOr);
        System.out.println("Person is > 18 or > 25 " + eligible);


        // Test for equality
        Predicate<String> stringPredicate = Predicate.isEqual("String");
        System.out.println(stringPredicate.test("String"));

        // The above is literally equal to
        @SuppressWarnings("ConstantValue")
        Predicate<String> lambdaPredicate = p -> p.equals("String");
        System.out.println(lambdaPredicate.test("String"));

    }
}
