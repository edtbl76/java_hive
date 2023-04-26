package DeployingVerticles_4;

import io.vertx.core.Vertx;

public class Main_4 {

    /*
        This is our main thread.
        This is responsible for deploying the Deployer Verticle.
        The deployerVerticle is a verticle that is build specifically for deploying other verticles.

        This is a basic demonstration of how Verticle Deployment is an asynchronous operation.
     */
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Deployer());
    }
}
