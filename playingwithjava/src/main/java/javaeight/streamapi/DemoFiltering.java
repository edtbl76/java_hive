package javaeight.streamapi;

import javaeight.streamapi.utils.Person;
import utils.Generated;

import java.util.ArrayList;
import java.util.List;

@Generated
@SuppressWarnings("java:S106")
public class DemoFiltering {


    public static void basicFilterExample() {
        /*
            Parameter:  Predicate applied to each element to determine if it should be included
            Return:     Stream consisting of the elements that match the predicate.


         */
        List<Integer> list = new ArrayList<>(List.of(1, 12, 23, 45, 6));
        list.stream()
                .filter(num -> num % 2 == 0)
                .forEach(System.out::println);
    }

    public static void customObjFilterExample() {

        List<Person> list = new ArrayList<>(List.of(
                new Person("Edward", 46),
                new Person("Vanessa", 41),
                new Person("Michael", 22),
                new Person("Connor", 11)
        ));

        list.stream()
                .filter(person -> person.getAge() > 18)
                .forEach(System.out::println);
    }

    public static void chainFilteringExample() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            list.add(i);
        }

        List<Integer> filtered = list.stream()
                // evens only
                .filter(integer -> integer % 2 == 0)
                // numbers divisible by 5
                .filter(integer -> integer % 5 == 0)
                .toList();

        System.out.println(filtered);

    }

    public static void main(String[] args) {

        //1. basic filter example
        basicFilterExample();

        //2. custom objects
        customObjFilterExample();

        //3. chain filtering
        chainFilteringExample();
    }

}
