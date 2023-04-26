@SuppressWarnings("FieldCanBeLocal")
public class VolatileMod implements Runnable{

    private volatile boolean active;

    public void run() {
        /*
        Usually run is called from one thread (the one started using Runnable!
        and stop() is called from another thread.

        If the cached value of active is used (from line 1 above) then the loop might not stop if you
        set active to false in  line 2.

        This is why you use volatile.
         */
        active = true;
        while (active) {
            System.out.println("Don't let this run forever.");
            active = false;
        }
    }

    public static void main(String[] args) {
        VolatileMod vm = new VolatileMod();
        vm.run();
    }
}
