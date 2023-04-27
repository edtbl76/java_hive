package javaeight.optionals;

import javaeight.optionals.utils.DumDumException;
import javaeight.optionals.utils.Person;
import javaeight.optionals.utils.Player;
import utils.Generated;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Optional.*;

@Generated
@SuppressWarnings({"java:S106", "java:S3655"})
public class OptionalOperationsDemo {

    public static final String DEFAULT_VALUE = "DefaultValue!";
    public static final String ORANGE = "orange";
    public static final String BANANA = "banana";
    Map<Integer, Person> personMap = new HashMap<>();
    @SuppressWarnings({"ConstantValue", "ThrowablePrintedToSystemOut", "DataFlowIssue"})
    public static void main(String[] args) throws DumDumException {

        // setup
        OptionalOperationsDemo demo = new OptionalOperationsDemo();
        Optional<Person> person = demo.getPerson(1);
        Optional<Player> player = Optional.of(new Player("Geno Smith", 1_200_000));
        Optional<String> nullOptional = empty();
        Optional<String> fruit = Optional.ofNullable(ORANGE);

        /*
            1. ifPresent()
                - no parameter
                - returns boolean
                    false if null
                    true if value is present
         */
        String result = person.isPresent()
                ? person.get().getName()
                : "No value";
        System.out.println(result);

        /*
            2. ifPresent(Consumer consumer)
                - Consumer as param
                - void
                - if a value is present, the consumer is invoked w/ that value
                - else, nothing happens
         */
        demo.populateMap();
        person = demo.getPerson(1);
        person.ifPresent(System.out::println); // This will print, because there is a value!


        /*
            3. get()
            - returns <T> value if present else NoSuchElementException
            - no parameter
         */
        try {
            System.out.println(nullOptional.get());
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }

        /*
            4. orElse(T other)
            - parameter is a default value
            - returns a value if present, or the default value
         */
        System.out.println(nullOptional.orElse("You didn't provide a value stupid!"));


        /*
           5.  orElseGet(Supplier other)
            - parameter -> a Supplier that provides a default value or some other calculation
            - returns the value if present or whatever the supplier can get you.
         */
        System.out.println(nullOptional.orElseGet(OptionalOperationsDemo::getDefaultValue));

        /*
            6. orElseThrow(Supplier other)
            - parameter -> This is like orElseGet() but it throws an exception if there is no value
         */

        try {
            System.out.println(nullOptional.orElseThrow(() -> new DumDumException("Forgot a value")));
        } catch (DumDumException e) {
            System.out.println(e);
        }

        /*
            7. filter(Predicate predicate)
            - used to check if the value matches the predicate parameter
            - if it does it returns Optional<T>, where T is the type of the correc value
            - if the value doesn't exist, or doesn't meet the predicate, Optional.empty() is returned.
         */
        Predicate<String> equalsOrange = s -> s.equals(ORANGE);
        Predicate<String> equalsBanana = s -> s.equals(BANANA);
        System.out.println(fruit.filter(equalsOrange));
        System.out.println(fruit.filter(equalsBanana));

        /*
            8. map(Function mapper)
            - if value is present, then map value based on present
                - if result is non-null, return value else return Optional.empty()
            - if no value is present, do nothing.
         */
        person
                .map(Person::getAge)
                .filter(integer -> integer > 25)
                .ifPresent(integer -> System.out.println("You're old!"));

        /*
            9. flatMap(Function mapper)
            - same concept as map, but w/ a flatMap.
            - map() will only transform values when they are unwrapped.
            - flatMap() can take a wrapped value, unwrap it and then transform it.
         */
        player
                .flatMap(Player::getSalary)
                .filter(integer -> integer > 1_000_000)
                .ifPresent(System.out::println);


    }

    @SuppressWarnings("SameParameterValue")
    private Optional<Person> getPerson(Integer id) {
        return ofNullable(personMap.get(id));
    }

    private void populateMap() {
        personMap.put(1, new Person("Edward", 46));
    }

    public static String getDefaultValue() {
        return DEFAULT_VALUE;
    }


}
