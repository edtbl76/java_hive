package org.tbl.lifecycle;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class AppLifecycleBean {

    @Inject
    MyOtherBean bean;

    void onStart(@Observes StartupEvent event) {
        log.info("The application is starting...{}", bean.hello());
    }

    void onStop(@Observes ShutdownEvent event) {
        log.info("The app is stopping...{}", bean.bye());
    }
}
