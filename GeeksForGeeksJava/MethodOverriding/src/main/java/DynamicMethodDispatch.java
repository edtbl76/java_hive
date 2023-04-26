class DMDBase {
    void method() {
        System.out.println("Inside Base Class method()");
    }
}

class DMDChildOne extends DMDBase {

    // override!
    void method() {
        System.out.println("Inside Child One's method()");
    }
}

class DMDChildTwo extends DMDBase {

    // override!
    void method() {
        System.out.println("Inside Child Two's Method()");
    }
}

public class DynamicMethodDispatch {

    public static void main(String[] args) {

        // Instantiate Base
        DMDBase dmdBase = new DMDBase();

        // Child One
        DMDChildOne dmdChildOne = new DMDChildOne();

        // Child Two
        DMDChildTwo dmdChildTwo = new DMDChildTwo();

        // Reference of Base
        DMDBase reference;

        /*
            Assign reference to the Base type, and call it's method()
         */
        reference = dmdBase;
        reference.method();

        /*
            Repeat for Child One
         */
        reference = dmdChildOne;
        reference.method();

        /*
            And... Child Two
         */
        reference = dmdChildTwo;
        reference.method();


    }
}
