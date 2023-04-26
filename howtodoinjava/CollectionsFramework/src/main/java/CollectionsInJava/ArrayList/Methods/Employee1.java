package CollectionsInJava.ArrayList.Methods;

import java.time.LocalDate;

public class Employee1 {

    private String name;
    private LocalDate dob;

    public Employee1(String name, LocalDate dob) {
        super();
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Name: " + name + " [" + dob.toString() + "]";
    }
}
