package com.oop.Polymorphism;

public class polyMorphismRuntimeExample {

    /*
        This is an example of Dynamic Binding/Method Overriding... RUNTIME POLYMORPHISM

            - RUNTIME => DYNAMIC. That part is intuitive.
            - Method OverRIDING OverR <-- Letter R for Runtime.

            - COMPILE => STATIC. That part is intuitive.
            - Method OverLoading <-- No fucking clue. LOAD? COMPILE? CLOSE ENOUGH?
     */
    public static void main(String[] args) {
        Animal a1 = new Cat();
        a1.makeNoise();

        Animal a2 = new Dog();
        a2.makeNoise();
    }

}

class Animal{
    public void makeNoise()  { System.out.println("Default Sound."); }
}

class Dog extends Animal {
    @Override
    public void makeNoise() { System.out.println("Bark!"); }
}

class Cat extends Animal {
    @Override
    public void makeNoise() { System.out.println("Meow!"); }
}