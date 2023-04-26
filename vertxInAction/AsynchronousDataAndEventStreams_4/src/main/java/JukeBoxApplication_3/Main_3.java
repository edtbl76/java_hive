package JukeBoxApplication_3;

import io.vertx.core.Vertx;

public class Main_3 {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new JukeBox());
        vertx.deployVerticle(new NetController());
    }
}
