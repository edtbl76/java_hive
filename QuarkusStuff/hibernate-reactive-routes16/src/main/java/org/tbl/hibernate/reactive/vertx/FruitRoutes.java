package org.tbl.hibernate.reactive.vertx;


import io.quarkus.vertx.web.Body;
import io.quarkus.vertx.web.Param;
import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.jbosslog.JBossLog;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.inject.Inject;

import java.util.List;
import java.util.NoSuchElementException;

import static io.quarkus.vertx.web.Route.HandlerType.FAILURE;
import static io.vertx.core.http.HttpMethod.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Vert.x Web, Hibernate Reactive and Mutiny
 */
@JBossLog
@RouteBase(path = "/fruits", produces = APPLICATION_JSON)
public class FruitRoutes {

    @Inject
    Mutiny.Session session;

    @Route(methods = GET, path = "/")
    public Uni<List<Fruit>> getAll() throws Exception {
        /*
            NOTE:
                - This makes sense for SMALL queries.

                For large queries it makes sense to use Multi<?> for result streams.
         */
        return session
                .createNamedQuery(Fruit.FIND_ALL, Fruit.class)
                .getResultList();
    }


    @Route(methods = GET, path = "/:id")
    public Uni<Fruit> getSingle(@Param String id) {
        return session.find(Fruit.class, Integer.valueOf(id));
    }

    @Route(methods = POST, path = "/")
    public Uni<Fruit> create(@Body Fruit fruit, HttpServerResponse response) {
        if (fruit == null || fruit.getId() != null) {
            return Uni.createFrom().failure(new IllegalArgumentException("Fruit id invalidly set on request"));
        }

        return session
                .persist(fruit)
                .chain(session::flush)
                .onItem().transform(ignore -> {
                    response.setStatusCode(201);
                    return fruit;
                });
    }

    @Route(methods = PUT, path = "/:id")
    public Uni<Fruit> update(@Body Fruit fruit, @Param String id) {
        if (fruit == null || fruit.getName() == null) {
            return Uni.createFrom().failure(new IllegalArgumentException("Fruit name not set on request"));
        }

        return session
                .find(Fruit.class, Integer.valueOf(id))
                .onItem().ifNotNull()
                .transformToUni(entity -> {
                    entity.setName(fruit.getName());
                    return session.flush()
                            .onItem()
                            .transform(ignore -> entity);
                })
                .onItem().ifNull()
                .fail();
    }

    @Route(methods = DELETE, path = "/:id")
    public Uni<Fruit> delete(@Param String id, HttpServerResponse response) {
        return session
                .find(Fruit.class, Integer.valueOf(id))
                .onItem().ifNotNull()
                .transformToUni(entity -> session.remove(entity)
                        .chain(session::flush)
                        .map(ignore -> {
                            response.setStatusCode(204).end();
                            return entity;
                        }))
                .onItem().ifNull().fail();
    }

    @Route(path = "/*", type = FAILURE)
    public void error(RoutingContext context) {
        Throwable throwable = context.failure();

        if (throwable != null) {
            log.error("Failed to handle request", throwable);

            int code = context.statusCode();
            String chunk = "";
            if (throwable instanceof NoSuchElementException) {
                code = 404;
            } else if (throwable instanceof IllegalArgumentException) {
                code = 422;
                chunk = new JsonObject().put("code", code)
                        .put("exceptionType", throwable.getClass().getName())
                        .put("error", throwable.getMessage())
                        .encode();
            }

            context.response().setStatusCode(code).end(chunk);
        } else {
            context.next();
        }
    }
}
