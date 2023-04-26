package org.tbl.artemis.jms;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/prices")
public class PriceResource {

    @Inject
    PriceConsumer consumer;

    @GET
    @Path("last")
    @Produces(TEXT_PLAIN)
    public String last() {
        return consumer.getLastPrice();
    }
}
