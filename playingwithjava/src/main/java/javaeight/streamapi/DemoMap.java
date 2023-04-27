package javaeight.streamapi;

import utils.Generated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

@Generated
@SuppressWarnings("java:S106")
public class DemoMap {

    static List<String> list = new ArrayList<>(
            List.of("Edward", "Vanessa", "Michael", "Connor", "Gravy"));
    static List<List<String>> lists = new ArrayList<>(
            List.of(Arrays.asList("a", "b", "c"),
                    Arrays.asList("d", "e", "f"),
                    Arrays.asList("g", "h", "i"))
    );

    public static void mapExample() {

        list.stream()
                .map(String::toUpperCase)
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

    }

    public static void mapPrimitivesExample() {

        /*
            Note when using mapTo<Prim> methods, it returns
                the primitive variations on the stream (IntStream, LongStream, DoubleStream, etc.)
         */
        list.stream()
                .mapToInt(String::length)
                .forEach(value -> System.out.print(value + " "));
        System.out.println();
    }

    /*
            Easiest way to explain a flatMap()

                Stream<String[]>        -> flatMap  ->  Stream<String>
                Stream<Set<String>>     -> flatMap  ->  Stream<String>
                Stream<List<String>>    -> flatMap  ->  Stream<String>


            WHY?
            - intermediate methods like filter(), distinct(),etc. don't support streams of Collections.
            - we have to flatten the stream before using it on the collection.


            flatMap has primitive versions too:

                - flatMapToInt
                - flatMapToLong
                - flatMapToDouble
    */
    public static void noFlatMapExample() {




        Stream<List<String>> listStream = lists.stream();

        // filter() doesn't work on streams of collections, so nothing gets printed.
        listStream
                .filter(strings -> "a".equals(strings.toString()))
                .forEach(System.out::println);

    }

    public static void flatMapExample() {
        lists.stream()
                .flatMap(Collection::stream)
                .filter("a"::equals)
                .forEach(s -> System.out.print(s + " "));
        System.out.println();
    }

    public static void main(String[] args) {

        //1. Basic Map Example.
        mapExample();

        //2. Mapping Objects to Primitives
        mapPrimitivesExample();

        //3. No Flat Map example (i.e. streams of collections)
        noFlatMapExample();

        //4. Flat Map!
        flatMapExample();


    }
}
