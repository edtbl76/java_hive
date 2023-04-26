package org.tbl.hibernate.orm.multitenant;

import io.quarkus.arc.Unremovable;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.jbosslog.JBossLog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@JBossLog
@RequestScoped
@Unremovable
public class CustomTenantResolver implements TenantResolver {

    @Inject
    RoutingContext context;

    @Override
    public String getDefaultTenantId() {
        return "base";
    }

    @Override
    public String resolveTenantId() {
        String path = context.request().path();
        final String tenantId;

        if (path.startsWith("/mycompany")) {
            tenantId = "mycompany";
        } else {
            tenantId = getDefaultTenantId();
        }
        log.debugv("TenandId = {0}", tenantId);
        return tenantId;
    }
}
