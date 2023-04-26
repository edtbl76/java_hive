package ClassAndInterface;

// Example of a Generic Class, but there is no Type Safety here
public class DemoClass {
    private Object t;

    public void set(Object t) {
        this.t = t;
    }

    public Object get() {
        return t;
    }
}
