package org.tbl.sns;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.tbl.sns.model.Quark;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@JBossLog
@Path("/sync/cannon")
@Produces(MediaType.TEXT_PLAIN)
public class QuarksCannonSyncResource {

    @Inject
    SnsClient client;

    @ConfigProperty(name = "topic.arn")
    String topicArn;

    static ObjectWriter QUARK_WRITER = new ObjectMapper().writerFor(Quark.class);

    @POST
    @Path("/shoot")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response publish(Quark quark) throws Exception {
        String message = QUARK_WRITER.writeValueAsString(quark);
        PublishResponse response = client.publish(builder -> builder.topicArn(topicArn).message(message));

        log.infov("Fired Quark[{0}, {1}}]", quark.getFlavor(), quark.getSpin());

        return Response.ok().encoding(response.messageId()).build();
    }
}
