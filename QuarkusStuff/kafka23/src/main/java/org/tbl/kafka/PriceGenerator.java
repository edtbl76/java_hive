package org.tbl.kafka;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

import static java.time.Duration.ofSeconds;

/*
    bean the produces random prices every 5 seconds
    - written to Kafka TOPIC.
    - Kafka config is in app props
 */
@ApplicationScoped
public class PriceGenerator {

    private final Random random = new Random();

    @Outgoing("generated-price")
    public Multi<Integer> generate() {
        return Multi.createFrom().ticks().every(ofSeconds(5))
                .onOverflow().drop()
                .map(tick -> random.nextInt(100));
    }
}
