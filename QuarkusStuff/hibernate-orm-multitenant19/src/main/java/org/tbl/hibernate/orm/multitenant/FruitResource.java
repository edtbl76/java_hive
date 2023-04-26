package org.tbl.hibernate.orm.multitenant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.jbosslog.JBossLog;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

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
@ApplicationScoped
@Path("/")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FruitResource {

    @Inject
    EntityManager entityManager;


    /*
        GET
     */
    @GET
    @Path("fruits")
    public Fruit[] getDefault() {
        return get();
    }

    @GET
    @Path("{tenant}/fruits")
    public Fruit[] getTenant() {
        return get();
    }

    private Fruit[] get() {
        return entityManager.createNamedQuery("Fruits.findAll", Fruit.class)
                .getResultList().toArray(new Fruit[0]);
    }

    /*
        GET SINGLE
     */
    @GET
    @Path("fruits/{id}")
    public Fruit getSingleDefault(@PathParam("id") Integer id) {
        return findById(id);
    }

    @GET
    @Path("{tenant}/fruits/{id}")
    public Fruit getSingleTenant(@PathParam("id") Integer id) {
        return findById(id);
    }

    private Fruit findById(Integer id) {
        Fruit entity = entityManager.find(Fruit.class, id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id [" + id + "] not found.", 404);
        }
        return entity;
    }

    /*
        CREATE
     */
    @POST
    @Transactional
    @Path("fruits")
    public Response createDefault(Fruit fruit) {
        return create(fruit);
    }

    @POST
    @Transactional
    @Path("{tenant}/fruits")
    public Response createTenant(Fruit fruit) {
        return create(fruit);
    }

    private Response create(Fruit fruit) {
        if (fruit.getId() != null) {
            throw new WebApplicationException("Id invalidly set on request", 422);
        }
        log.debugv("Create {0}", fruit.getName());

        entityManager.persist(fruit);
        return Response.ok(fruit).status(201).build();

    }

    /*
        UPDATE
     */
    @PUT
    @Transactional
    @Path("fruits/{id}")
    public Fruit updateDefault(@PathParam("id") Integer id, Fruit fruit) {
        return update(id, fruit);
    }

    @PUT
    @Transactional
    @Path("{tenant}/fruits/{id}")
    public Fruit updateTenant(@PathParam("id") Integer id, Fruit fruit) {
        return update(id, fruit);
    }

    private Fruit update(@PathParam Integer id, Fruit fruit) {
        if (fruit.getName() == null) {
            throw new WebApplicationException("Fruit name not set on request", 422);
        }

        Fruit entity = entityManager.find(Fruit.class, id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id [" + id + "] not found.", 404);
        }
        entity.setName(fruit.getName());

        log.debugv("Update #{0} {1}", fruit.getId(), fruit.getName());
        return entity;
    }

    /*
        DELETE
     */
    @DELETE
    @Path("fruits/{id}")
    @Transactional
    public Response deleteDefault(@PathParam("id") Integer id) {
        return delete(id);
    }

    @DELETE
    @Path("{tenant}/fruits/{id}")
    @Transactional
    public Response deleteTenant(@PathParam("id") Integer id) {
        return delete(id);
    }

    private Response delete(Integer id) {
        Fruit entity = entityManager.find(Fruit.class, id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id [" + id + "] not found.", 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build();
    }

    /*
        FIND BY
     */
    @GET
    @Path("fruitsFindBy")
    public Response findByDefault(@QueryParam("type") String type, @QueryParam("value") String value) {
        return findBy(type, value);
    }

    @GET
    @Path("{tenant}/fruitsFindBy")
    public Response findByTenant(@QueryParam("type") String type, @QueryParam("value") String value) {
        return findBy(type, value);
    }

    private Response findBy(String type, String value) {
        if (!"name".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException("Currently only 'fruitsFindBy?type=name' is supported");
        }

        List<Fruit> list = entityManager.createNamedQuery("Fruits.findByName", Fruit.class)
                .setParameter("name", value)
                .getResultList();

        if (list.size() == 0) {
            return Response.status(404).build();
        }
        Fruit fruit = list.get(0);
        return Response.status(200).entity(fruit).build();
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
