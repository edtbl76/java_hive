package org.tbl.artemis.jms;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.SECONDS;
import static javax.jms.Session.AUTO_ACKNOWLEDGE;

// Bean that produces random prices every 5 seconds and sending them to the prices JMS queue
/*
    JMS = Java Message Service
 */
@ApplicationScoped
public class PriceProducer implements Runnable {

    @Inject
    ConnectionFactory connectionFactory;

    private final Random random = new Random();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    void onStart(@Observes StartupEvent event) {
        scheduler.scheduleWithFixedDelay(this, 0L, 5L, SECONDS);
    }

    void onStop(@Observes ShutdownEvent event) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(AUTO_ACKNOWLEDGE)) {
            context
                    .createProducer()
                    .send(context.createQueue("prices"), Integer.toString(random.nextInt(100)));
        }
    }
}
