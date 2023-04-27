package oop.abstraction.interfaces.solvingdiamondproblem;

public interface InterfaceOne {

    /*
        Default methods can be overridden by their concrete
        impl. Since classes can implement multiple interfaces,
        this causes a collision.
     */
    @SuppressWarnings("java:S106")
    default void print() {
        System.out.println("Interface One");
    }
}
