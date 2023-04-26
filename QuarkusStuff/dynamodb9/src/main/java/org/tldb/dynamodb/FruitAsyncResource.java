package org.tldb.dynamodb;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/async-fruits")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FruitAsyncResource {

    @Inject
    FruitAsyncService service;

    @GET
    public Uni<List<Fruit>> getAll() {
        return service.findAll();
    }

    @GET
    @Path("{name}")
    public Uni<Fruit> getSingle(@PathParam("name") String name) {
        return service.get(name);
    }

    @POST
    public Uni<List<Fruit>> add(Fruit fruit) {
        return service.add(fruit)
                .onItem().ignore().andSwitchTo(this::getAll);
    }

}
