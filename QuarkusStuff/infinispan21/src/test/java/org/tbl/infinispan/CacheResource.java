package org.tbl.infinispan;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.Map;

import static java.time.Duration.ofMillis;

public class CacheResource implements QuarkusTestResourceLifecycleManager {

    private static GenericContainer<?> INFINISPAN = null;
    private static final Integer INFINISPAN_PORT = 11222;
    private static final String INFINISPAN_CONTAINER_VERSION = "infinispan/server:11.0.4.Final";

    private static RemoteCacheManager cacheManager;

    @Override
    public Map<String, String> start() {
        INFINISPAN =
                new GenericContainer<>(DockerImageName.parse(INFINISPAN_CONTAINER_VERSION))
                        .waitingFor(new LogMessageWaitStrategy().withRegEx(".*Infinispan Server.*started in.*\\s"))
                        .withStartupTimeout(ofMillis(20000))
                .withEnv("USER", "Titus Bramble")
                .withEnv("PASS", "Shambles");

        INFINISPAN.start();
        final String hosts = INFINISPAN.getContainerIpAddress() + ":" + INFINISPAN.getMappedPort(INFINISPAN_PORT);
        return Collections.singletonMap("quarkus.infinispan-client.server-list", hosts);
    }

    @Override
    public void stop() {
        INFINISPAN.stop();
    }
}
