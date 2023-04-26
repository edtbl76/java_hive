class Animal {
    public void move() {
        System.out.println("I am part of the method of the base class");
    }
}

class Dog extends Animal {
    public void move(){
        System.out.println("I'm overrding Animal. I'm going to run the super.move() just to show you my parent");

        // This is kind of cool. I'm calling the class I'm overriding from within the class that is overriding it.
        // Polymorphism is fun.
        super.move();
    }
}

public class OverridingEx1 {

    public static void main(String[] args) {
        Animal a = new Dog();
        a.move();
    }
}
