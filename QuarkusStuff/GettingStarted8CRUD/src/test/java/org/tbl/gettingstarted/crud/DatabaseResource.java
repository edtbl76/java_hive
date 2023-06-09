package org.tbl.gettingstarted.crud;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Collections;
import java.util.Map;

public class DatabaseResource implements QuarkusTestResourceLifecycleManager {

    public static final PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>("postgres:10.5")
            .withDatabaseName("quarkus_test")
            .withUsername("quarkus_test")
            .withPassword("quarkus_test");

    @Override
    public Map<String, String> start() {
        DATABASE.start();
        return Collections.singletonMap(
                "quarkus.datasource.reactive.url", DATABASE.getJdbcUrl().replace("jdbc:", ""));
    }

    @Override
    public void stop() {
        DATABASE.stop();
    }
}
