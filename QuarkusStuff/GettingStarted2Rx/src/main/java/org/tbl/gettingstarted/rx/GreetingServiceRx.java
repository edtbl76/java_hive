package org.tbl.gettingstarted.rx;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;

@ApplicationScoped
public class GreetingServiceRx {

    public Uni<String> greeting(String name) {
        return Uni.createFrom().item(name)
                .onItem().transform(s -> String.format("Hello %s, reactively", name));
    }

    public Multi<String> greetings(int count, String name) {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1))
                .onItem().transform(s -> String.format("hello %s - %d%n", name, s))
                .transform().byTakingFirstItems(count);
    }
}
