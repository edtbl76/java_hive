package CollectionsInJava.Comparable;

public class Person implements Comparable<Person> {

    private Long id;
    private String name;

    public Person(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(Person o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }
}
