package BasicClusterExample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.ignite.IgniteClusterManager;
import org.apache.ignite.IgniteLogger;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.logger.slf4j.Slf4jLogger;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;


public class BasicConsumer extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(BasicConsumer.class);

    public static void main(String[] args) {
        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpCommunicationSpi communicationSpi = new TcpCommunicationSpi();

        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        spi.setIpFinder(ipFinder);
        communicationSpi.setLocalPort(45701);
        ipFinder.setAddresses(Arrays.asList(
                "127.0.0.1",
                "127.0.0.1:45700..45709"
        ));
        spi.setIpFinder(ipFinder);




        IgniteConfiguration igniteCfg = new IgniteConfiguration();
        IgniteLogger igniteLogger = new Slf4jLogger();
        igniteCfg.setGridLogger(igniteLogger);
        igniteCfg.setDiscoverySpi(spi);
        igniteCfg.setCommunicationSpi(communicationSpi);


        ClusterManager clusterManager = new IgniteClusterManager(igniteCfg);
        VertxOptions options = new VertxOptions();
        options.setEventBusOptions(new EventBusOptions().setClustered(true))
            .setClusterManager(clusterManager);

        Vertx.clusteredVertx(options, vertxAsyncResult -> {
            if(vertxAsyncResult.succeeded()) {
                Vertx vertx = vertxAsyncResult.result();
                vertx.deployVerticle(BasicConsumer.class.getName());
            } else {
                vertxAsyncResult.cause().printStackTrace();
            }
        });
    }

    @Override
    public void start() throws Exception {

        EventBus bus = vertx.eventBus();

        bus.consumer("news", message -> System.out.println("News on consumer1: " + message.body()));
        bus.consumer("news", message -> System.out.println("News on consumer2: " + message.body()));
        bus.consumer("news", message -> System.out.println("News on consumer3: " + message.body()));
        System.out.println("Ready!");
    }
}
