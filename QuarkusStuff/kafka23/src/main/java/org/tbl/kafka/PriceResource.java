package org.tbl.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.*;

/*
    Simple resource that retrieves in mem "my-data-stream" and sends items to a server sent event
 */
@Path(("/prices"))
public class PriceResource {

    @Inject
    @Channel("my-data-stream")
    Publisher<Double> prices;

    @GET
    @Path("stream")
    @Produces(SERVER_SENT_EVENTS)
    @SseElementType(TEXT_PLAIN)
    public Publisher<Double> stream() {
        return prices;
    }
}
