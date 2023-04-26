package org.tbl.kafka.panache;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/prices")
public class PriceResource {

    @GET
    @Produces(APPLICATION_JSON)
    public List<Price> getAllPrices() {
        return Price.listAll();
    }
}
