package org.tbl.gettingstarted.rx;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.*;

@Path("/hello")
public class GreetingResourceRx {

    @Inject
    GreetingServiceRx service;

    @GET
    @Produces(TEXT_PLAIN)
    @Path("greeting/{name}")
    public Uni<String> greeting(String name) {
        return service.greeting(name);
    }


    @GET
    @Produces(TEXT_PLAIN)
    @Path("greeting/{count}/{name}")
    public Multi<String> greetings(int count, String name) {
        return service.greetings(count, name);
    }

    @GET
    @Produces(SERVER_SENT_EVENTS)
    @Path("/stream/{count}/{name}")
    public Multi<String> greetingsAsStream(int count, String name) {
        return service.greetings(count, name);
    }

    @GET
    @Produces(TEXT_PLAIN)
    public String hello() {
        return "hello";
    }
}
