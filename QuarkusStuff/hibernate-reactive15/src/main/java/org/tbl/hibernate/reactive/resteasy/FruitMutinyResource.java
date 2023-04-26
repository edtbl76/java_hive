package org.tbl.hibernate.reactive.resteasy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.smallrye.mutiny.Uni;
import lombok.extern.jbosslog.JBossLog;
import org.hibernate.reactive.mutiny.Mutiny;
import org.jboss.resteasy.reactive.RestPath;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import java.util.List;
import java.util.function.Function;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@JBossLog
@Path("fruits")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FruitMutinyResource {

    @Inject
    Mutiny.Session session;

    @GET
    public Uni<List<Fruit>> get() {
        return session
                .createNamedQuery("Fruits.findAll", Fruit.class)
                .getResultList();
//                .getResults()
//                .collectItems()
//                .asList();
    }

    @GET
    @Path("{id}")
    public Uni<Fruit> getSingle(@RestPath Integer id) {
        return session.find(Fruit.class, id);
    }

    @POST
    public Uni<Response> create(Fruit fruit) {
        if (fruit == null || fruit.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request", 422);
        }

        return session
                .persist(fruit)
                .chain(session::flush)
                .map(ignore -> Response.ok(fruit).status(201).build());
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(@RestPath Integer id, Fruit fruit) {
        if (fruit == null || fruit.getName() == null) {
            throw new WebApplicationException("Fruit name was not set on request", 422);
        }

        // Update function (never returns null)
        Function<Fruit, Uni<? extends Response>> update = entity -> {
            entity.setName(fruit.getName());
            return session.flush()
                    .onItem()
                    .transform(ignore -> Response.ok(entity).build());
        };

        return session
                .find(Fruit.class, id)
                // If entity exists
                .onItem().ifNotNull()
                .transformToUni(update)
                // else
                .onItem().ifNull()
                .continueWith(Response.ok().status(404).build());
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@RestPath Integer id) {

        // Delete Function never returns null
        Function<Fruit, Uni<? extends Response>> delete = entity ->
                session
                        .remove(entity)
                        .chain(session::flush)
                        .onItem().transform(ignore -> Response.ok().status(204).build());

        return session
                .find(Fruit.class, id)
                // If Entity exists
                .onItem().ifNotNull()
                .transformToUni(delete)
                // else
                .onItem().ifNull()
                .continueWith(Response.ok().status(404).build());

    }


    /**
     * Create a HTTP response from an exception.
     *
     * Response Example:
     *
     * <pre>
     * HTTP/1.1 422 Unprocessable Entity
     * Content-Length: 111
     * Content-Type: application/json
     *
     * {
     *     "code": 422,
     *     "error": "Fruit name was not set on request.",
     *     "exceptionType": "javax.ws.rs.WebApplicationException"
     * }
     * </pre>
     */
    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception e) {
            log.error("Failed to handle request", e);

            int code = 500;
            if (e instanceof WebApplicationException) {
                code = ((WebApplicationException) e).getResponse().getStatus();
            }

            ObjectNode exceptionJson = objectMapper.createObjectNode();
            exceptionJson.put("exceptionType", e.getClass().getName());
            exceptionJson.put("code", code);

            if (e.getMessage() != null) {
                exceptionJson.put("error", e.getMessage());
            }

            return Response.status(code)
                    .entity(exceptionJson)
                    .build();
        }
    }

}
