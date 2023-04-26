// Base/Parent/Super
class Dad {
    void method() {
        System.out.println("Parent's method()");
    }
}

// Inherited/Child/Sub
class Kid extends Dad {

    @Override
    void method() {
        System.out.println("Child's method()");
    }
}


public class RunTimePolymorphism {
    public static void main(String[] args) {

        /*
            If the base type reference refers to the Base object, then
            the base version of the method is called
         */
        Dad dad = new Dad();
        dad.method();

        /*
            If the base type reference refers to the Child object, then
            the CHILD version of the method is called.
         */
        Dad otherDad = new Kid();
        otherDad.method();
    }
}

