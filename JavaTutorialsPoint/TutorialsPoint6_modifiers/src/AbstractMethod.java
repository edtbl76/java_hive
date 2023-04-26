abstract class AbstractMethod {
    @SuppressWarnings("unused")
    abstract void m(); // abstract method
}

class Implementer extends AbstractMethod {
    // implements abstract method

    void m() {
        System.out.println("Peekaboo!");
    }

    public static void main(String[] args) {
        Implementer i = new Implementer();
        i.m();
    }
}
