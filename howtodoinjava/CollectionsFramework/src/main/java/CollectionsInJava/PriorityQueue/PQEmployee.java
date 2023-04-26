package CollectionsInJava.PriorityQueue;

import java.time.LocalDate;

public class PQEmployee implements Comparable<PQEmployee> {
    private Integer id;
    private String name;
    private LocalDate dob;

    public PQEmployee(Integer id, String name, LocalDate dob) {
        super();
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
    public int compareTo(PQEmployee o) {
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + "]";
    }
}
