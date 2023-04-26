package BehavioralDesignPatterns.TemplateMethod.RW_Comparable_0;

import BehavioralDesignPatterns.Strategy.RW_Comparator_0.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("Duplicates")
public class EverydayDemo {

    private static void displayInfo(List<Person> people) {
        people.forEach(person -> System.out.println(person.getName()));
    }

    public static void main(String[] args) {
        Person ed = new Person(43, "Ed", "978-999-9990");
        Person paul = new Person(40, "Paul", "978-999-9991");
        Person anthony = new Person(27, "Anthony", "978-999-9992");

        List<Person> people = Arrays.asList(ed, paul, anthony);

        System.out.println(" --- Not Sorted");
        displayInfo(people);

        /*

            // This is the Anonymous inner class - It's quite verbose, but I'm leaving it here, because it
            is easier to understand than the terse code I'm going to use

            Collections.sort(people, new Comparator<Person>() {
                @Override
                public int compare(Person o1, Person o2) {
                    if (o1.getAge() > o2.getAge()) {
                        return 1;
                    } else if (o1.getAge() < o2.getAge()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });


      ***      OPTIMIZE 1 : convert anonymous inner class to a lambda w/ a BiFunction

            Collections.sort(people, (o1, o2) -> {
            if (o1.getAge() > o2.getAge()) {
                return 1;
            } else if (o1.getAge() < o2.getAge()) {
                return -1;
            } else
                return 0;
            });

       ***     OPTIMIZE 2 : we can reduce the cyclomatic complexity using Integer.compare()

           Collections.sort(people, (o1, o2) -> {
            return Integer.compare(o1.getAge(), o2.getAge());
            });

       ***     OPTIMIZE 3 : We can replace the lambda with Comparator.comparingInt() and a Method assignment

            Collections.sort(people, Comparator.comparingInt(Person::getAge));


        ***     OPTIMIZE 4 :

            replace Collections.sort() with List.sort()

            people.sort(Comparator.comparingInt(Person::getAge));

        */

        people.sort(Comparator.comparingInt(Person::getAge));

        System.out.println("\n --- Sorted by age");
        displayInfo(people);

        people.sort(Comparator.comparing(Person::getName));
        System.out.println("\n --- Sorted by name");
        displayInfo(people);

    }
}
