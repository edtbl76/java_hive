package multithreading.examples;

import utils.Generated;

@Generated
public class Employee2 {

    // shared variables
    private String name;
    private final Object lock = new Object();

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized void resetName() {
        this.name = "";
    }

    public String getName() {
        synchronized (lock) {
            return this.name;
        }
    }
}
