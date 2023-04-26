package org.tbl.ses;

import org.tbl.ses.model.Email;
import software.amazon.awssdk.services.ses.SesClient;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.*;

@Path("/sync")
@Produces(TEXT_PLAIN)
@Consumes(APPLICATION_JSON)
public class QuarkusSesSyncResource {

    @Inject
    SesClient ses;

    @POST
    @Path("email")
    public String encrypt(Email data) {
        return ses.sendEmail(req -> req
            .source(data.getFrom())
            .destination(builder -> builder.toAddresses(data.getTo()))
            .message(builder -> builder
                .subject(sub -> sub.data(data.getSubject()))
                .body(b -> b.text(txt -> txt.data(data.getBody()))))).messageId();
    }
}
