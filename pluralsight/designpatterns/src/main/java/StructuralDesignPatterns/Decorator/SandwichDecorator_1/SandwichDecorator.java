package StructuralDesignPatterns.Decorator.SandwichDecorator_1;

/*
    Abstract Base for all decorators. Note, this implements our Component
    That's the guts of the Decorator
 */
public abstract class SandwichDecorator implements Sandwich {

    /*
        Composition,
     */
    protected Sandwich customSandwich;

    public SandwichDecorator(Sandwich customSandwich) {
        this.customSandwich = customSandwich;
    }

    public String make() {
        return customSandwich.make();
    }
}
