package org.tbl.kafka;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

import static org.eclipse.microprofile.reactive.messaging.Acknowledgment.Strategy.*;

/*
    Bean that consumes data from Kafka "prices" topic
    Applies a conversion
    publishes result to in mem "my-data-stream" stream
*/
@ApplicationScoped
public class PriceConverter {

    private static final double CONVERSION_RATE = 0.88;

    // Consume from PRICES channel and produce to MY-DATA-STREAM channel
    @Incoming("prices")
    @Outgoing("my-data-stream")
    // Send to all
    @Broadcast
    // Ack messages before calling the method
    @Acknowledgment(PRE_PROCESSING)
    public double process(int priceInUsd) {
        return priceInUsd * CONVERSION_RATE;
    }
}
