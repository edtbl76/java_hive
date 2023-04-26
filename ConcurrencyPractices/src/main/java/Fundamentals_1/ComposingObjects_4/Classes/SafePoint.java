package Fundamentals_1.ComposingObjects_4.Classes;

//@ThreadSafe
public class SafePoint {
    //@GuardedBy("this")
    private int x;
    private int y;

    private SafePoint(int[] a) {
        this(a[0], a[1]);
    }

    public SafePoint(SafePoint safePoint) {
        this(safePoint.get());
    }

    public SafePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /*
        This is a cool concurrency trick.
        - This solves CHECK-THEN-ACT problems of multivariable invariants, by returning both values (x and y)
        simultaneously as elements of an array.
        - This prevents inconsistency/safety issues
     */
    public synchronized int[] get() {
        return new int[] { x, y };
    }

    public synchronized void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
