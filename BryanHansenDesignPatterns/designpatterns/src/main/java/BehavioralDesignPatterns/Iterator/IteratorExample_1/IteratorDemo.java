package BehavioralDesignPatterns.Iterator.IteratorExample_1;

import java.util.Iterator;

public class IteratorDemo {

    public static void main(String[] args) {

        GuitarRepository repository = new GuitarRepository();

        repository.addGuitar("Ibanez");
        repository.addGuitar("Gibson");
        repository.addGuitar("Jackson");

        Iterator<String> iterator = repository.iterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        repository.forEach(System.out::println);

    }
}
