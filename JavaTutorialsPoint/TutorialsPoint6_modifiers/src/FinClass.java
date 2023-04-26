@SuppressWarnings("unused")
public final class FinClass {

    private FinClass() {
        System.out.println("Can't subclass this... du nu nu nu.");
    }

    public static void main(String[] args) {
        FinClass fc = new FinClass();
    }
}
