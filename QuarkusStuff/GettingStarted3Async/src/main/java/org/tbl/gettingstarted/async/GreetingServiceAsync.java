package org.tbl.gettingstarted.async;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;

import static io.smallrye.mutiny.infrastructure.Infrastructure.getDefaultExecutor;

@ApplicationScoped
public class GreetingServiceAsync {

    public Uni<String> greeting(String name) {
        return Uni.createFrom().item("Hello " + name)
                .emitOn(getDefaultExecutor());
    }
}
