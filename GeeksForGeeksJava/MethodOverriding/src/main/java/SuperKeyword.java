public class SuperKeyword {
    public static void main(String[] args) {

        /*
            Based on the "rules of reference", the code below this should use the method of the reference type
            - therefore it will execute the Child class method().

            However, since I'm calling super() inside that method, I'm going to execute both.
         */
        SKBase base = new Satellite();
        base.method();
    }
}

class SKBase {
    void method() {
        System.out.println("SKBase method()");
    }
}

class Satellite extends SKBase {
    @Override
    void method() {
        super.method();
        System.out.println("Satellite method()");
    }
}

