package org.tldb.dynamodb;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.List;

import static javax.ws.rs.core.MediaType.*;

@Path("/fruits")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FruitResource {

    @Inject
    FruitSyncService service;

    @GET
    public List<Fruit> getAll() {
        return service.findAll();
    }

    @GET
    @Path("{name}")
    public Fruit getSingle(@PathParam("name") String name) {
        return service.get(name);
    }

    @POST
    public List<Fruit> add(Fruit fruit) {
        service.add(fruit);
        return getAll();
    }
}
