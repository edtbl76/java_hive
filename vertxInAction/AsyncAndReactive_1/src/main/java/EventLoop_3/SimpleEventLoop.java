package EventLoop_3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class SimpleEventLoop {

    public static void main(String[] args) {
        EventLoop eventLoop = new EventLoop();

        /*
            Create a thread that dispatches a new event to the event loop per second
            (up to 6 seconds for this example)
         */
        new Thread(() -> {
            IntStream.range(0, 6).forEach(i -> {
                delay(1000);
                eventLoop.dispatch(new EventLoop.Event("tick", i));
            });
            eventLoop.dispatch(new EventLoop.Event("stop", null));
        }).start();

        /*
            This is a separate thread that is going to dispatch 2 events
                - 1 at 2.5 seconds
                - another at 3.3 seconds.
         */
        new Thread(() -> {
            delay(2500);
            eventLoop.dispatch(new EventLoop.Event("hello", "beautiful world"));
            delay(800);
            eventLoop.dispatch(new EventLoop.Event("hello", "beautiful universe"));
        }).start();

        /*
            2 threads dispatched from the Main() thread.
         */
        eventLoop.dispatch(new EventLoop.Event("hello", "world"));
        eventLoop.dispatch(new EventLoop.Event("foo", "bar"));

        /*
            the on() method is for defining event handlers, which we are providing as inline anonymous functions (lambdas!)
         */
        eventLoop
                .on("hello", s-> System.out.println("hello " + s))
                .on("tick", n -> System.out.println("tick #" + n))
                .on("stop", v -> eventLoop.stop())
                .run();
        System.out.println("Bye!");
    }

    // separates exception logic from main method.
    private static void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}

final class EventLoop {

    // Queue is our mini event stream
    private final ConcurrentLinkedDeque<Event> events = new ConcurrentLinkedDeque<>();

    // this is a hashmap of event handlers. You could look at this as a loose implementation of the Strategy pattern
    private final ConcurrentHashMap<String, Consumer<Object>> handlers = new ConcurrentHashMap<>();

    // This is an IoC implementation, where the "client code" sends the desired handler to the loop and we return
    // the eventloop object w/ the updates handler store.
    public EventLoop on(String key, Consumer<Object> handler) {
        handlers.put(key, handler);
        return this;
    }

    // this dispatches events to our "event stream"/"event store"
    public void dispatch(Event event) {
        events.add(event);
    }

    // sends interrupt() to stop a slept thread.
    public void stop() {
        Thread.currentThread().interrupt();
    }

    // this is our Thread executor.
    public void run() {
        // as long as the event store isn't empty or interrupted....
        while (!(events.isEmpty() && Thread.interrupted())) {

            // If not empty, get the topmost event from the event store.
            if (!events.isEmpty()) {
                Event event = events.pop();

                // if there is an event handler for the  event, then process the event.
                if (handlers.containsKey(event.key)) {
                    handlers.get(event.key).accept(event.data);

                // tell the user to piss off and specify a proper handler.
                } else {
                    System.err.println("No handler for key " + event.key);
                }
            }
        }
    }

    // storage container for an event. the key is a string and the data is the handler (which is a lambda in this demo)
    static final class Event {
        private final String key;
        private final Object data;

        public Event(String key, Object data) {
            this.key = key;
            this.data = data;
        }
    }
}