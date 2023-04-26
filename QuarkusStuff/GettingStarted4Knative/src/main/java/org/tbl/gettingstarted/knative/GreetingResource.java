package org.tbl.gettingstarted.knative;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.*;

@Path("/")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(TEXT_PLAIN)
    public String hello() {
        return "Hello";
    }

}
