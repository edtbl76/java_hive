package oop.abstraction.interfaces.solvingdiamondproblem;

public class DiamondDriver implements InterfaceOne, InterfaceTwo {

    @SuppressWarnings("java:S106")
    @Override
    public void print() {

        // Option 1 - > Override both
        System.out.println("Driver");

        // Option 2 -> Pick one of the existing impls
        InterfaceOne.super.print();
        InterfaceTwo.super.print();
    }
}
