package org.tbl;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.*;

@Path("/prices")
public class PriceResource {

    @Inject
    @Channel("my-data-stream")
    Publisher<Double> prices;

    @GET
    @Produces(TEXT_PLAIN)
    public String hello() {
        return "No Prices Here, Chief.";
    }

    @GET
    @Path("stream")
    @Produces(SERVER_SENT_EVENTS)
    public Publisher<Double> stream() {
        return prices;
    }

}
