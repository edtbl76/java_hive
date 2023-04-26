package org.tbl.kafka.streams.aggregator.streams;


import io.debezium.kafka.KafkaCluster;
import io.debezium.util.Testing;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.io.File;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class KafkaResource implements QuarkusTestResourceLifecycleManager {

    private KafkaCluster cluster;

    @Override
    public Map<String, String> start() {
        try {
            Properties properties = new Properties();
            properties.setProperty("zookeeper.connection.timeout.ms", "45000");
            properties.setProperty("group.min.session.timeout.ms", "100");

            File directory = Testing.Files.createTestingDirectory("kafka-data", true);

            cluster = new KafkaCluster()
                    .withPorts(2182, 9092)
                    .addBrokers(1)
                    .usingDirectory(directory)
                    .deleteDataUponShutdown(true)
                    .withKafkaConfiguration(properties)
                    .deleteDataPriorToStartup(true)
                    .startup();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        if (cluster != null) {
            cluster.shutdown();
        }
    }
}
