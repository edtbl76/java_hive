package Ignite_8.FromIgniteDocs.Clustering_8.TCPIPDiscovery_5;

import io.reactivex.functions.Consumer;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBusOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.spi.cluster.ignite.IgniteClusterManager;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.communication.tcp.TcpCommunicationSpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import java.util.Collections;

public class MultiClusterOnSameMachine2 extends AbstractVerticle {

    static IgniteConfiguration igniteConfiguration = new IgniteConfiguration();

    public static void main(String[] args) {
        vertSetup();
    }

    @Override
    public void start() {
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:49500..49520"));

        discoverySpi
                .setLocalPort(49500)
                .setLocalPortRange(20)
                .setIpFinder(ipFinder);

        TcpCommunicationSpi communicationSpi = new TcpCommunicationSpi();
        communicationSpi
                .setLocalPort(49100);

        igniteConfiguration
                .setDiscoverySpi(discoverySpi)
                .setCommunicationSpi(communicationSpi)
                .setIgniteInstanceName("TwoClustersOneSystemTwo");

        try (Ignite ignite = Ignition.start(igniteConfiguration)) {
            System.out.println(ignite.configuration().getIgniteInstanceName());
            System.out.println(ignite.configuration().getDiscoverySpi().consistentId());
            System.out.println(ignite.configuration().getCommunicationSpi().getOutboundMessagesQueueSize());
        }
    }

    private static void vertSetup() {

        ClusterManager clusterManager = new IgniteClusterManager();
        EventBusOptions eventBusOptions = new EventBusOptions().setClustered(true);
        VertxOptions vertxOptions = new VertxOptions()
                .setEventBusOptions(eventBusOptions)
                .setClusterManager(clusterManager);


        Consumer<Vertx> runner = cv -> {
            try {
                cv.deployVerticle(MultiClusterOnSameMachine2.class.getName());
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        };

        Vertx.clusteredVertx(vertxOptions, vertxAsyncResult -> {
            if (vertxAsyncResult.succeeded()) {
                try {
                    runner.accept(vertxAsyncResult.result());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                vertxAsyncResult.cause().printStackTrace();
            }
        });
    }


}
