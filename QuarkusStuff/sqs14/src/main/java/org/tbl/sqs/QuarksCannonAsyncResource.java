package org.tbl.sqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.smallrye.mutiny.Uni;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.tbl.sqs.model.Quark;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

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
    SqsAsyncClient client;

    @ConfigProperty(name = "queue.url")
    String queueUrl;

    static ObjectWriter QUARK_WRITER = new ObjectMapper().writerFor(Quark.class);

    @POST
    @Path("shoot")
    @Consumes(APPLICATION_JSON)
    public Uni<Response> sendMessage(Quark quark) throws Exception {
        String message = QUARK_WRITER.writeValueAsString(quark);

        return Uni.createFrom()
                .completionStage(client.sendMessage(builder -> builder.queueUrl(queueUrl).messageBody(message)))
                .onItem().invoke(response ->
                        log.infov("Fired Quark[{0}, {1}}]", quark.getFlavor(), quark.getSpin()))
                .onItem().transform(SendMessageResponse::messageId)
                .onItem().transform(id -> Response.ok().entity(id).build());
    }
}
