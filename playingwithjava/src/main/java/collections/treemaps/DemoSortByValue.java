package collections.treemaps;

import utils.Generated;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Generated
@SuppressWarnings({"java:S106", "ComparatorMethodParameterNotUsed"})
public class DemoSortByValue {

    @SuppressWarnings("java:S1604")
    public static SortedMap<String, Integer> sortByValues(SortedMap<String, Integer> map) {

        @SuppressWarnings("Convert2Lambda") Comparator<String> valueComparator = new Comparator<>() {
            @Override
            public int compare(String key1, String key2) {

                int comparison = map.get(key1).compareTo(map.get(key2));
                return comparison == 0 ? 1 : comparison;
            }
        };

        TreeMap<String, Integer> mapSortedByValues = new TreeMap<>(valueComparator);
        mapSortedByValues.putAll(map);
        return mapSortedByValues;
    }

    public static SortedMap<String, Integer> sortByValuesLambda(SortedMap<String, Integer> map) {

        Comparator<String> valueComparator = (key1, key2) -> {

            int comparison = map.get(key1).compareTo(map.get(key2));
            return comparison == 0 ? 1 : comparison;
        };

        TreeMap<String, Integer> mapSortedByValues = new TreeMap<>(valueComparator);
        mapSortedByValues.putAll(map);
        return mapSortedByValues;
    }

    public static void main(String[] args) {


        TreeMap<String, Integer> ranked = new TreeMap<>(Map.of(
                "Jones", 1,
                "Volkanovski", 2,
                "Makhachev", 3,
                "Edwards", 4,
                "Usman", 5,
                "Adesanya", 6,
                "Pereira", 7,
                "Sterling", 8,
                "Oliveira", 9,
                "Moreno", 10
        ));

        ranked.putAll(Map.of(
                "Prochazka", 11,
                "Poirier", 12,
                "Hill", 13,
                "Whittaker", 14,
                "Holloway", 15
        ));

        System.out.println("Initial Map: " + ranked);

        System.out.println("Sorted By Anon Function: " + sortByValues(ranked));
        System.out.println("Sorted By Lambda : " + sortByValuesLambda(ranked));

    }
}
