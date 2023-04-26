class Box<T> {
    private T t;

    void add(T t) {
        this.t = t;
    }

    T get() {
        return t;
    }
}

public class GenericClassesDemo {

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox = new Box<>();

        integerBox.add(10);
        stringBox.add("Hello World");

        System.out.printf("Integer value: %d\n\n", integerBox.get());
        System.out.printf("String value: %s\n\n", stringBox.get());
    }
}
