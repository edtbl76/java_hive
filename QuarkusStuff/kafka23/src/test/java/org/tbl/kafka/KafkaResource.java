package org.tbl.kafka;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.Map;

public class KafkaResource implements QuarkusTestResourceLifecycleManager {

    private static final String KAFKA_CONTAINER_VERSION = "confluentinc/cp-kafka:6.1.0";


    private final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse(KAFKA_CONTAINER_VERSION));

    @Override
    public Map<String, String> start() {
        kafka.start();
        return Collections.singletonMap("kafka.bootstrap.servers", kafka.getBootstrapServers());
    }

    @Override
    public void stop() {
        kafka.close();
    }
}
