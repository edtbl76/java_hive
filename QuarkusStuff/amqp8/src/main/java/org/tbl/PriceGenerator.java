package org.tbl;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;


@ApplicationScoped
public class PriceGenerator {

    private final Random random = new Random();

    @Outgoing("generated-price")
    public Multi<Integer> generate() {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(2))
                .onOverflow().drop()
                .map(tick -> random.nextInt(100));
    }
}


