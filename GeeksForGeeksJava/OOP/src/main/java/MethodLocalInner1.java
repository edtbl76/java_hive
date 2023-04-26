class MLI1Outer {

    void outer() {
        System.out.println("Outer!");

        class MLI1Inner {
            void inner() {
                System.out.println("Inner!");
            }
        }

        MLI1Inner inner = new MLI1Inner();
        inner.inner();
    }

}

public class MethodLocalInner1 {

    public static void main(String[] args) {
        MLI1Outer outer = new MLI1Outer();
        outer.outer();
    }
}
