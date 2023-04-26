abstract class Fart {
    abstract void cheekMovement();
}

class ButtTrumpet extends Fart {

    void cheekMovement() {
        System.out.println("Thbbbbbbbbbbb... cheeks are flapping.");
    }
}

public class AbstractMethodsExample {

    public static void main(String[] args) {
        ButtTrumpet buttTrumpet = new ButtTrumpet();
        buttTrumpet.cheekMovement();
    }
}
