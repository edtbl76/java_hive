abstract class AnonymousInner {
    public abstract void mymethod();
}


public class OverrideMethodOfClassAnonInnerClass {

    public static void main(String[] args) {
        // Anonymous Inner Class is declared and instantiated at the same time!
        AnonymousInner inner = new AnonymousInner() {
            public void mymethod() {
                System.out.println("This is an example of an anonymous inner class!");
            }
        };
        // We are calling mymethod here which overrides the abstract method.
        inner.mymethod();
    }

}
