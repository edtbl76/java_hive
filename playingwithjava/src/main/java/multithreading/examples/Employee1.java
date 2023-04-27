package multithreading.examples;

import utils.Generated;

@Generated
public class Employee1 {

    // shared variable
    private String name;

    // method is synchronized on 'this' object
    public synchronized void setName(String name) {
        this.name = name;
    }

    // also synchronized on same object
    public synchronized void resetName() {
        this.name = "";
    }

    // Equivalent of adding synchronized in the method signature.
    public String getName() {
        synchronized (this) {
            return this.name;
        }
    }

}
