package MockitoExamples.BasicStuff;

public class ExampleClass {
    private int uniqueId;

    public ExampleClass(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void testing(int integer) {}

    public void someMethod(String string) {}
}
