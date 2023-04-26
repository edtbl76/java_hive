package CollectionsInJava.Comparator;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortListOfObjects {

    public static void main(String[] args) {
        ArrayList<ComparablePerson> list = new ArrayList<>();
        list.add(new ComparablePerson(1, "Ed", LocalDate.of(1976, Month.OCTOBER, 15)));
        list.add(new ComparablePerson(2, "Vanessa", LocalDate.of(1981, Month.OCTOBER, 29)));
        list.add(new ComparablePerson(3, "Michael", LocalDate.of(2000, Month.NOVEMBER, 19)));
        list.add(new ComparablePerson(4, "Connor", LocalDate.of(2011, Month.JULY, 25)));
        System.out.println("PreSort: " + list);

        list.sort(new NameSorter());
        System.out.println("ByName: " + list);

        list.sort(new NameSorter().reversed());
        System.out.println("Reversed: " + list);

        // different syntax for sort by name
        list.sort(Comparator.comparing(ComparablePerson::getName));
        System.out.println("By Name: (Diff)" + list);

        // Group_By_Sort
        list.add(new ComparablePerson(5, "Ed", LocalDate.now()));
        list.add(new ComparablePerson(6, "Vanessa", LocalDate.now()));
        list.add(new ComparablePerson(7, "Michael", LocalDate.of(2015, Month.NOVEMBER, 11)));
        list.add(new ComparablePerson(8, "Connor", LocalDate.of(2011, Month.JULY, 25)));
        System.out.println("Updated: " + list);


        Comparator<ComparablePerson> groupByComparator = Comparator.comparing(ComparablePerson::getName)
                .thenComparing(ComparablePerson::getDob)
                .thenComparing(ComparablePerson::getId);

        list.sort(groupByComparator);
        System.out.println("Group-By-Sort: " + list);
    }
}
