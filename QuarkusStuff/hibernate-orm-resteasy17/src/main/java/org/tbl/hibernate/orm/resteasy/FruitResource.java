package org.tbl.hibernate.orm.resteasy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.jbosslog.JBossLog;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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
@Path("fruits")
@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FruitResource {

    @Inject
    EntityManager entityManager;

    @GET
    public List<Fruit> get() {
        return entityManager
                .createNamedQuery("Fruits.findAll", Fruit.class)
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Fruit getSingle(@PathParam Integer id) {
        Fruit entity = entityManager.find(Fruit.class, id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id [" + id + "] does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Fruit fruit) {
        if (fruit.getId() != null) {
            throw new WebApplicationException("Id invalidly set on request", 422);
        }

        entityManager.persist(fruit);
        return Response.ok(fruit).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Fruit update(@PathParam Integer id, Fruit fruit) {
        if (fruit.getName() == null) {
            throw new WebApplicationException("Fruit Name not set on request", 422);
        }

        Fruit entity = entityManager.find(Fruit.class, id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id [" + id + "] does not exist", 404);
        }
        entity.setName(fruit.getName());
        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Integer id) {
        Fruit entity = entityManager.getReference(Fruit.class, id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id [" + id + "] does not exist", 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build();
    }


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

            return Response.status(code).entity(exceptionJson).build();
        }
    }
}
