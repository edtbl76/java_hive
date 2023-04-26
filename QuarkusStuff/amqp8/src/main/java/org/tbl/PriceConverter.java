package org.tbl;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PriceConverter {

    private static final double CONVERSION_RATE = 0.88;

    // Consume from the prices channel and produce to my-data-stream channel
    @Incoming("prices")
    @Outgoing("my-data-stream")
    // send to all subscribers
    @Broadcast
    // Ack messages mefore calling the method
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public double process(int priceInUsd) {
        return priceInUsd * CONVERSION_RATE;
    }


}
