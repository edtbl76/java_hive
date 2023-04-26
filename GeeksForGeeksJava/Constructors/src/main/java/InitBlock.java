@SuppressWarnings("ALL")
class IBNoChain {

    {
        System.out.println("I'm common code!");
    }

    IBNoChain() {
        System.out.println("Default no-args constructor");
    }

    IBNoChain(int number) {
        System.out.println("Parameterized Constructor: " + number);
    }
}


public class InitBlock {
    public static void main(String[] args) {

        // Call default
        new IBNoChain();

        // Call parameterized
        new IBNoChain(25);
    }
}
