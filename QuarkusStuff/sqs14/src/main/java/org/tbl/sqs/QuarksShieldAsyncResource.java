package org.tbl.sqs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.smallrye.mutiny.Uni;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.tbl.sqs.model.Quark;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@JBossLog
@Path("/async/shield")
public class QuarksShieldAsyncResource {

    @Inject
    SqsAsyncClient client;

    @ConfigProperty(name = "queue.url")
    String queueUrl;

    static ObjectReader QUARK_READER = new ObjectMapper().readerFor(Quark.class);


    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Uni<List<Quark>> receive() {
        return Uni.createFrom().completionStage(client.receiveMessage(
                builder -> builder.maxNumberOfMessages(10).queueUrl(queueUrl)))
                .onItem().transform(ReceiveMessageResponse::messages)
                .onItem().transform(messages -> messages
                        .stream()
                        .map(Message::body)
                        .map(this::toQuark)
                        .collect(Collectors.toList()));
    }

    private Quark toQuark(String message) {
        Quark quark = null;
        try {
            quark = QUARK_READER.readValue(message);
        } catch (Exception e) {
           log.error("Failed to decode message", e);
           throw new RuntimeException(e);
        }

        return quark;
    }
}
