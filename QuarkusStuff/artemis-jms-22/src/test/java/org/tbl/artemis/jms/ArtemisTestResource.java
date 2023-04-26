package org.tbl.artemis.jms;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.apache.activemq.artemis.core.server.embedded.EmbeddedActiveMQ;
import org.apache.commons.io.FileUtils;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

import static org.awaitility.Awaitility.await;

public class ArtemisTestResource implements QuarkusTestResourceLifecycleManager {

    private EmbeddedActiveMQ embeddedActiveMQ;

    @Override
    public Map<String, String> start() {
        try {
            FileUtils.deleteDirectory(Paths.get("./target/artemis").toFile());
            embeddedActiveMQ = new EmbeddedActiveMQ();
            embeddedActiveMQ.start();

            await().until(
                    () -> embeddedActiveMQ.getActiveMQServer().isActive()
                            && embeddedActiveMQ.getActiveMQServer().isStarted());
            System.out.println("Artemis server started");
        } catch (Exception e) {
            throw new RuntimeException("Failed to start embedded ActiveMQ server", e);
        }
        return Collections.emptyMap();
    }

    @Override
    public void stop() {
        try {
            embeddedActiveMQ.stop();
            System.out.println("Artemis server stopped");
        } catch (Exception e) {
            throw new RuntimeException("Failed to stop embedded ActiveMQ server", e);
        }

    }
}
