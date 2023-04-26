package CollectionsInJava.Array.Examples;

import java.io.Serializable;

public class Lineman implements Serializable  {

    private String position;
    private String firstName;
    private String lastName;

    public Lineman(String position, String firstName, String lastName) {
        this.position = position;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        return "[" + position + "] " + String.join(" ", firstName, lastName);
    }
}
