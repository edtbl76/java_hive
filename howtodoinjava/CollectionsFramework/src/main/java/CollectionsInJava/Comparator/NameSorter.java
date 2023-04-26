package CollectionsInJava.Comparator;

import java.util.Comparator;

public class NameSorter implements Comparator<ComparablePerson> {

    @Override
    public int compare(ComparablePerson o1, ComparablePerson o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
