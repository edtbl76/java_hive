@SuppressWarnings("unused")
interface Animal {
    void eat();
    void travel();
}

class Mammal implements Animal {

    public void eat(){
        System.out.println("nomnom");
    }

    public void travel(){
        System.out.println("Trudge trudge.");
    }
}

public class ImplementingInterfaces {

    public static void main(String[] args) {
        Mammal m = new Mammal();
        m.eat();
        m.travel();
    }

}
