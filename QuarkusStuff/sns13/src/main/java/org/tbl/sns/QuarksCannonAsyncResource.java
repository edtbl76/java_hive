package org.tbl.sns;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.smallrye.mutiny.Uni;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.tbl.sns.model.Quark;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@JBossLog
@Path("/async/cannon")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class QuarksCannonAsyncResource {

    @Inject
    SnsAsyncClient client;

    @ConfigProperty(name = "topic.arn")
    String topicArn;

    static ObjectWriter QUARK_WRITER = new ObjectMapper().writerFor(Quark.class);

    @POST
    @Path("shoot")
    @Consumes(APPLICATION_JSON)
    public Uni<Response> publish(Quark quark) throws Exception {
        String message = QUARK_WRITER.writeValueAsString(quark);

        return Uni.createFrom()
                .completionStage(client.publish(builder -> builder.topicArn(topicArn).message(message)))
                .onItem().invoke(publishResponse ->
                        log.infov("Fired Quark[{0}, {1}}]", quark.getFlavor(), quark.getSpin()))
                .onItem().transform(PublishResponse::messageId)
                .onItem().transform(s -> Response.ok().entity(s).build());
    }
}
