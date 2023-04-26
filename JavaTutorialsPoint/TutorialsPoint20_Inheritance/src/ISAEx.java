class Animal {}
class Mammal extends Animal {}
class Reptile extends Animal {}
class Dog extends Mammal {}

public class ISAEx {

    // the print statements below are always true, because this is an example.
    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {
        Animal a = new Animal();
        Mammal m = new Mammal();
        Reptile r = new Reptile();
        Dog d = new Dog();

        System.out.println(a instanceof Animal);
        System.out.println(m instanceof Animal);
        System.out.println(r instanceof Animal);
        System.out.println(d instanceof Animal);

        System.out.println(m instanceof Mammal);
        System.out.println(d instanceof Mammal);

        System.out.println(r instanceof Reptile);

        System.out.println(d instanceof Dog);



    }
}
