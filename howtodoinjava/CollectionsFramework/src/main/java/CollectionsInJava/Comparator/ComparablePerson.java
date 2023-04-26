package CollectionsInJava.Comparator;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class ComparablePerson implements Serializable {
    private Integer id;
    private String name;
    private LocalDate dob;

    public ComparablePerson(Integer id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }


    @Override
    public String toString() {
        return "[id=" + id + "] " + name + " (" + dob + ")";
    }
}
