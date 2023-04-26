package VertXApplication_5;


import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;

public class VertxEcho {

    /*
        One of the most valuable aspects of Vert.x is that the event handlers are always executed on the same thread.
            - no need for AtomicIntegers
            - no need for JVM locks
     */
    private static int connections = 0;

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.createNetServer()
                // Creating a TCP server requires passing a callback for each new connection
                .connectHandler(VertxEcho::handleNewClient)
                .listen(3000);

        // This defines a periodic task by a callback being executed every 5 seconds.
        vertx.setPeriodic(5000, id -> System.out.println(howMany()));

        // HTTP server is configured by being given the callback to be executed for each request.
        vertx.createHttpServer()
                .requestHandler(request -> request.response().end(howMany()))
                .listen(8080);
    }

    private static void handleNewClient(NetSocket socket) {
        connections++;
        // Buffer handler is invoked every time a buffer is ready for consumption.
        /*
            Here we only have to write it back.
            We also check for the termination command. (w/ a convenient toString() method)
         */
        socket.handler(buffer -> {
            socket.write(buffer);
            if (buffer.toString().endsWith("/quit\n"))
                socket.close();
        });
        // decrement the conn counter.
        socket.closeHandler(v -> connections--);
    }

    private static String howMany() {
        return "We now have " + connections + " connections";
    }
}


