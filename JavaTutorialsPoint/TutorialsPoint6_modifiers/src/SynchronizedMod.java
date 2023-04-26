public class SynchronizedMod {

    private synchronized void spillMyGuts() {
        System.out.println("If you have more thn one thread, I can only access one of you at a time!");
    }

    private SynchronizedMod() { }

    public static void main(String[] args) {
        SynchronizedMod sm = new SynchronizedMod();
        sm.spillMyGuts();
    }
}
