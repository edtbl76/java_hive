class CTClass {

    /*
        Default Constructor
        - calls another constructor w/ (this)
     */
    CTClass() {
        this(5);
        System.out.println("The default constructor");
    }

    CTClass(int x) {
        this(5, 15);
        System.out.println(x);
    }

    CTClass(int x, int y) {
        System.out.println(x * y);
    }
}

class CTClassOrder2 {

    /*
        Default Constructor
     */
    CTClassOrder2() {
        System.out.println("The default construcator");
    }

    CTClassOrder2(int x) {
        System.out.println(x);
    }

    CTClassOrder2(int x, int y){
        this(5);
        System.out.println(x * y);
    }
}

public class ChainingThis {

    public static void main(String[] args) {

        // Invoke Constructor Chaining
        new CTClass();

        // Same concept, different order.
        new CTClassOrder2(8, 10);
    }
}
