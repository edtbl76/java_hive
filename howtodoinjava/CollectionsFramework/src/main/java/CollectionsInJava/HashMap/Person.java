package CollectionsInJava.HashMap;

import java.time.LocalDate;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public Person(int id, String firstName, String lastName, LocalDate dob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + lastName + "," + firstName + " (" + dob + ")";

    }
}
