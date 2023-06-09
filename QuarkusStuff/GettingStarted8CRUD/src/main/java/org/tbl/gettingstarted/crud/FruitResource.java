package org.tbl.gettingstarted.crud;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.net.URI;

import static javax.ws.rs.core.MediaType.*;
import static javax.ws.rs.core.Response.Status.*;

@Path("fruits")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FruitResource {

    @Inject
    @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
    boolean schemaCreate;

    @Inject
    PgPool client;

    @PostConstruct
    void config() {
        if (schemaCreate) {
            initdb();
        }
    }

    private void initdb() {
        client.query("DROP TABLE IF EXISTS fruits").execute()
                .flatMap(rows -> client.query("CREATE TABLE fruits (id SERIAL PRIMARY KEY, name TEXT NOT NULL)")
                .execute())
                .flatMap(rows -> client.query("INSERT INTO fruits (name) VALUES ('Raspberry')").execute())
                .flatMap(rows -> client.query("INSERT INTO fruits (name) VALUES ('Strawberry')").execute())
                .flatMap(rows -> client.query("INSERT INTO fruits (name) VALUES ('Lime')").execute())
                .flatMap(rows -> client.query("INSERT INTO fruits (name) VALUES ('Lemon')").execute())
                .await().indefinitely();
    }

    @GET
    public Multi<Fruit> get() {
        return Fruit.findAll(client);
    }

    @GET
    @Path("{id}")
    public Uni<Response> getSingle(@PathParam Long id) {
        return Fruit.findById(client, id)
                .onItem().transform(fruit -> fruit != null ? Response.ok(fruit) : Response.status(NOT_FOUND))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @POST
    public Uni<Response> create(Fruit fruit) {
        return fruit.save(client)
                .onItem().transform(id -> URI.create("/fruits/" + id))
                .onItem().transform(uri -> Response.created(uri).build());
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(@PathParam Long id, Fruit fruit) {
        return fruit.update(client)
                .onItem().transform(updated -> updated ? OK : NOT_FOUND)
                .onItem().transform(status -> Response.status(status).build());
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@PathParam Long id) {
        return Fruit.delete(client, id)
                .onItem().transform(deleted -> deleted ? NO_CONTENT : NOT_FOUND)
                .onItem().transform(status -> Response.status(status).build());
    }


}
