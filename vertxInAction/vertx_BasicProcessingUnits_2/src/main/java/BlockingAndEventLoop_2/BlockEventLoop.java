package BlockingAndEventLoop_2;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class BlockEventLoop extends AbstractVerticle  {

    /*
        This runs an infinite loop.

        VERTX COOL FEATURE # 7895
        - it has a built-in check that detects when an event loop has been blocked for too long
        (looks like 2s by default)
        - It warns you three times before it starts throwing exceptions

        io.vertx.core.VertxException: Thread blocked.
     */
    @Override
    public void start() throws Exception {
        vertx.setTimer(1000, id -> { while(true); });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new BlockEventLoop());
    }
}
