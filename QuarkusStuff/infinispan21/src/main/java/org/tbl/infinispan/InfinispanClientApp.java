package org.tbl.infinispan;

import io.quarkus.runtime.StartupEvent;
import lombok.extern.slf4j.Slf4j;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.configuration.XMLStringConfiguration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class InfinispanClientApp {

    private static final String CACHE_CONFIG = "<infinispan><cache-container>" +
            "<distributed-cache name=\"%s\"></distributed-cache>" +
            "</cache-container></infinispan>";
    @Inject
    RemoteCacheManager cacheManager;

    void onStart(@Observes StartupEvent event) {
        log.info("Create or get cache named mycache with the default configuration");

        RemoteCache<Object, Object> cache = cacheManager
                .administration()
                .getOrCreateCache("mycache",
                        new XMLStringConfiguration(String.format(CACHE_CONFIG, "mycache")));

        cache.put("hello", "Hello World, Infinispan is up!");
    }



}
