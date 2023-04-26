public class CopyConstructorExample {

    public static void main(String[] args) {

        // Create an object
        MyObject myObject1 = new MyObject(1, 2);
        System.out.println("MyObject1: " + myObject1);

        // Call the copy constructor
        MyObject myObject2 = new MyObject(myObject1);
        System.out.println("MyObject2: " + myObject2);

    }
}

class MyObject {

    private final int one;
    private final int two;

    // Normal Parameterized Constructor
    public MyObject(int one, int two) {
        this.one = one;
        this.two = two;
    }

    // Copy Constructor (Package Private)
    public MyObject(MyObject myObject) {
        one = myObject.one;
        two = myObject.two;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "one=" + one +
                ", two=" + two +
                '}';
    }
}
