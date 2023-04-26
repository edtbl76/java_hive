package org.tbl.kafka.panache;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

import static java.time.Duration.ofSeconds;

@ApplicationScoped
public class PriceGenerator {

    Random random = new Random();

    @Outgoing("generated-price")
    public Multi<Integer> generate() {
        return Multi.createFrom().ticks().every(ofSeconds(2))
                .map(tick -> random.nextInt(100));
    }
}
