import java.util.*;

class Pupil {

    private String name;
    private Integer age;

    public Pupil(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Pupil{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    static class SortingComparator implements Comparator<Pupil> {

        @Override
        public int compare(Pupil pupil1, Pupil pupil2) {

            // for comparison
            int nameComparison = pupil1.getName().compareTo(pupil2.getName());
            int ageComparison = pupil1.getAge().compareTo(pupil2.getAge());

            // 2-level comparison via ternary operator
            return (nameComparison == 0) ? ageComparison : nameComparison;
        }
    }
}

public class ComparatorSortMultipleFields {

    public static void main(String[] args) {

        List<Pupil> pupils = new ArrayList<>(List.of(
                new Pupil("Connor", 10),
                new Pupil("Michael", 21),
                new Pupil("Vanessa", 40),
                new Pupil("Edward", 45),
                new Pupil("Gravy", 2),
                new Pupil("James",9)
        ));

        // Iterate using iterator for giggles
        Iterator<Pupil> pupilIterator = pupils.listIterator();
        System.out.println("Before Sorting: ");
        while (pupilIterator.hasNext()) {
            System.out.println(pupilIterator.next());
        }

        // Sort em!
        pupils.sort(new Pupil.SortingComparator());
        System.out.println("After Sorting: ");
        pupils.forEach(System.out::println);
    }
}
