package org.tbl.gettingstarted.async;

import io.smallrye.mutiny.Uni;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static io.smallrye.mutiny.infrastructure.Infrastructure.*;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/hello")
public class GreetingResourceAsync {

    @Inject
    GreetingServiceAsync service;

    @GET
    @Produces(TEXT_PLAIN)
    @Path("greeting/{name}")
    public Uni<String> greeting(@PathParam String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(TEXT_PLAIN)
    public Uni<String> hello() {
        return Uni.createFrom().item(() -> "Hello")
                .emitOn(getDefaultExecutor());
    }
}
