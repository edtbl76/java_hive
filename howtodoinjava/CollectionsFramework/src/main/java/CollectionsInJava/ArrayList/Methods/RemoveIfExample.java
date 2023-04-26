package CollectionsInJava.ArrayList.Methods;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class RemoveIfExample {

    public static void main(String[] args) {

        // Removing Based ON Predicate Condition
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,19));

        // You don't have to do this. I just like creating Predicates.
        Predicate<Integer> predicate = (number -> number % 2 == 0);

        numbers.removeIf(predicate);
        System.out.println(numbers);

        // Removing based on objects field
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("Ed", LocalDate.of(1976, Month.OCTOBER, 15)));
        people.add(new Person("Someone Else", LocalDate.of(1981, Month.OCTOBER, 29)));

        Predicate<Person> condition = person -> !(person.getName().startsWith("E"));
        System.out.println(people);
        people.removeIf(condition);
        System.out.println(people);
    }
}

class Person {
    private String name;
    private LocalDate dob;

    Person(String name, LocalDate dob) {
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Name: " + name + " DOB: " + dob.toString();
    }
}