package org.tbl.artemis.jms;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.Getter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;

/*
    Bean that consumes prices from JMS queue
 */
@ApplicationScoped
public class PriceConsumer implements Runnable {

    @Inject
    ConnectionFactory connectionFactory;

    private final ExecutorService scheduler = Executors.newSingleThreadExecutor();

    @Getter
    private volatile String lastPrice;

    void onStart(@Observes StartupEvent event) {
        scheduler.submit(this);
    }

    void onStop(@Observes ShutdownEvent event) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(AUTO_ACKNOWLEDGE)) {
            JMSConsumer consumer = context.createConsumer(context.createQueue("prices"));

            while (true) {
                Message message = consumer.receive();
                if (message == null) {
                    return;
                }
                lastPrice = message.getBody(String.class);
                System.out.println("Got last price: " + lastPrice);
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
