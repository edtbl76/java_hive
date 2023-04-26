class MLI2Outer {

    void outer() {
        /*
            NOTE: prior to java 8, this variable had to be declared final in order to be accessed by
            Method Local Inner Methods. 

         */
        int x = 100;
        System.out.println("Outer!");

        class MLI2Inner {
            void inner() {
                System.out.println("x = " + x);
                System.out.println("Inner!");
            }
        }

        MLI2Inner inner = new MLI2Inner();
        inner.inner();
    }

}

public class MethodLocalInner2 {

    public static void main(String[] args) {
        MLI2Outer outer = new MLI2Outer();
        outer.outer();
    }
}
