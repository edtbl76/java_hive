package StructuralDesignPatterns.Flyweight.FlyweightExample_1;

// Instances of Item will be the Flyweights
public class Item {

    private final String name;

    /*
        intrinsic values. Immutable, internal value that can't be changed... name above is final
     */
    public Item(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
