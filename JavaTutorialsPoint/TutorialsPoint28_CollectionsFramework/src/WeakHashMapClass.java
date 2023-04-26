import java.util.WeakHashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class WeakHashMapClass {

    private static Map map;
    public static void main(String[] args) {

        map = new WeakHashMap();
        map.put("Maine", "August");

        Runnable runner = () -> {
            while (map.containsKey("Maine")) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) { }

                System.out.println("Thread waiting!");
                // This is necessary, because this system doesn't require much memory
                // so this thread is going to run until memory runs out.. looooooong time.
                // if we request gc, it will stop sooner.
                System.gc();
            }
        };

        Thread thread = new Thread(runner);
        thread.start();
        System.out.println("Main waiting");
        try {
            thread.join();
        } catch (InterruptedException ignored) { }
    }
}
