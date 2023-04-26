package StructuralDesignPatterns.Decorator.SandwichDecorator_1;

// Concrete Component
public class SimpleSandwich implements Sandwich {

    @Override
    public String make() {
        return "Bread";
    }
}
