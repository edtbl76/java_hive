package org.tbl.hibernate.orm.panache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.panache.common.Sort;
import lombok.extern.jbosslog.JBossLog;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
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

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@JBossLog
@Path("/fruits")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FruitResource {

    @GET
    public List<Fruit> get() {
        return Fruit.listAll(Sort.by("name"));
    }

    @GET
    @Path("{id}")
    public Fruit getSingle(@PathParam Long id) {
        Fruit entity = Fruit.findById(id);
    if (entity == null) {
            throw new WebApplicationException("Fruit with id [" + id + "] does not exist", 404);
        }

        return entity;
    }


    @POST
    @Transactional
    public Response create(Fruit fruit) {
        if (fruit.id != null) {
            throw new WebApplicationException("Id invalidly set on request", 422);
        }

        fruit.persist();
        return Response.ok(fruit).status(201).build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Fruit update(@PathParam Long id, Fruit fruit) {
        if (fruit.name == null) {
            throw new WebApplicationException("Fruit name not set on request", 422);
        }

        Fruit entity = Fruit.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id [" + id + "] does not exist", 404);
        }
        entity.name = fruit.name;
        return entity;
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete(@PathParam Long id) {
        Fruit entity = Fruit.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id [" + id + "] does not exist", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }

    @Provider
    public static class ErrorMapper implements ExceptionMapper<Exception> {

        @Inject
        ObjectMapper objectMapper;

        @Override
        public Response toResponse(Exception e) {
            log.error("Failed to handle request",e);

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

            return Response.status(code).entity(exceptionJson).build();

        }
    }
}
