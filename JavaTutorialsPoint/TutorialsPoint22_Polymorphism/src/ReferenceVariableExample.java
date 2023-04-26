interface Food {}
class Animal {}

// This is morbid
class Chicken extends Animal implements Food {}

public class ReferenceVariableExample {

    // A Chicken is an Animal   (as a subclass of a super class)
    // A Chicken is Food        (by implementing an interface)
    // A Chicken Is a Chicken   (self)
    // A Chicken is an Object   (all objects inherit java.lang.Object)

    @SuppressWarnings({"ConstantConditions", "UnnecessaryLocalVariable"})
    public static void main(String[] args) {
        Chicken foghorn = new Chicken();
        System.out.println(foghorn.getClass());             // this will return Chicken
        System.out.println(foghorn instanceof Chicken);     // this confirms we are in fact a Chicken

        Animal a = foghorn;
        System.out.println(a.getClass());                   // this will return Chicken
        System.out.println(a instanceof Animal);            // this confirms that we are also a subclass of Animal

        Food f = foghorn;
        System.out.println(f.getClass());                   // this will return Chicken
        System.out.println(f instanceof Food);              // this confirms that we have implemented the Food interface

        Object o = foghorn;
        System.out.println(o.getClass());                   // this will return Chicken
        System.out.println(o instanceof Object);            // this confirms that we are in fact a java.lang.Object
    }

}
