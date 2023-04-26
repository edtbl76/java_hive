package Misc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializableClass implements Serializable {

    private static final long serialVersionID = 1L;

    private String firstName = null;
    private String lastName = null;

    private List<String> permissions = new ArrayList<>(Arrays.asList("ADMIN", "USER"));

    public SerializableClass(final String first, final String last) {
        this.firstName = first;
        this.lastName = last;
    }

    public SerializableClass deepCopy() throws Exception {

        // Serialization of object
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(this);

        // De-Serialization of object
        ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bis);

        return (SerializableClass) in.readObject();

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
        return this.getFirstName() + "," + this.getLastName() + "," + permissions;
    }
}
