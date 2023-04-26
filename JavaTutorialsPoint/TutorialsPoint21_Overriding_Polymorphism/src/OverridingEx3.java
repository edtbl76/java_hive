class Mammal {
    public void move() {
        System.out.println("I am part of the method of the base class");
    }
}

class Wolf extends Mammal {
    public void move(){
        System.out.println("I'm overrding Mammal. I'm a wolf.");
    }

    public void bark(){
        System.out.println("Woof!");
    }
}

public class OverridingEx3 {

    public static void main(String[] args) {
        Mammal mammal = new Mammal();
        Mammal wolf = new Wolf();
        Wolf fox = new Wolf();

        mammal.move();
        wolf.move();
        fox.move();
        fox.bark();

        // To prove the Compile vs. Runtime nature of object instantiation above notice the following:
        /*
        fox.bark() works

        However, if we uncomment wolf.bark() we'll get:

            ERROR: cannot find symbol. This is because the Compiler only does a reference check
            at compile time, and the reference is of type MAMMAL, which doesn't have a bark() method.

         */
        //wolf.bark();

    }
}
