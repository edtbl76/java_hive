package BehavioralDesignPatterns.Iterator.IteratorExample_1;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class GuitarRepository implements Iterable<String> {

    private String[] guitars;
    private int index;

    public GuitarRepository() {
        guitars = new String[10];
        index = 0;
    }

    public void addGuitar(String guitar) {
        if (index == guitars.length) {
            String[] largerRepo = new String[guitars.length + 5];
            System.arraycopy(guitars, 0, largerRepo, 0, guitars.length);
            guitars = largerRepo;
            largerRepo = null;
        }

        guitars[index] = guitar;
        index++;
    }

    @Override
    public Iterator<String> iterator() {
        Iterator<String> iterator = new Iterator<>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                /*
                    this is a safety check
                    - make sure index is less than the data structure length
                    - make sure the value at the given index is not null
                 */
                return currentIndex < guitars.length && guitars[currentIndex] != null;
            }

            @Override
            public String next() {
                /*
                    This is a twofer.
                    post increment does the increment work AFTER returning the value
                    1.) give me the current value
                    2.) increment it.
                 */
                return guitars[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not Gonna Take It");
            }
        };

        return iterator;
    }

//    @Override
//    public void forEach(Consumer action) {
//
//    }
//
//    @Override
//    public Spliterator spliterator() {
//        return null;
//    }
}
