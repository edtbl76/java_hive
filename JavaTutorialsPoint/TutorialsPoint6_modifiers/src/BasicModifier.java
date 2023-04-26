@SuppressWarnings("unused")
public class BasicModifier {

    private boolean someBoolean;            // class var, only available in this class.
    private static final double temp = 96.7;        // static var, package-private (no modifier)
    private static final int WIDTH = 42;  // static var available to class and subclass only.

    @SuppressWarnings("ConstantConditions")
    private BasicModifier() {
        System.out.println(someBoolean);
    }

    public static void main(String[] args) {
        System.out.println(temp + WIDTH);

        /* Remember! You can't access a non-static variable from a static context, because we are outside of
           the scope. Static variables are available at program exec, but non static class vars need
           an instance of the class...
        */
        @SuppressWarnings("unused")
        BasicModifier bm = new BasicModifier();  // this prints our class var. (someBoolean)
    }

}
