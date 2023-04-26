public class OverridingEx2 {

    public static void main(String[] args) {
        // package level access to the classes in OverridingEx1

        // This seems the same, but it is NOT!
        /*
        The first case is where the object reference is to Animal, but the actual data type stored is Dog.

        The reference (left-side piece) is managed at COMPILE TIME (this is the part where the compiler
        checks its REFERENCE)

        The value (right-side piece) isn't assigned until a call is made to instantiate, therefore it is
        determined at runtime.

        In the first case, we have a reference to Animal, but the value is Dog
        In the second case, both the reference and the value are to Dog.
         */
        Animal a = new Dog();
        a.move();

        Dog b = new Dog();
        b.move();

    }
}
