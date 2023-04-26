package org.tbl.sqs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.tbl.sqs.model.Quark;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;

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
@Path("/sync/shield")
public class QuarksShieldSyncResource {

    @Inject
    SqsClient client;

    @ConfigProperty(name = "queue.url")
    String queueUrl;

    static ObjectReader QUARK_READER = new ObjectMapper().readerFor(Quark.class);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public List<Quark> receive() {
        List<Message> messages = client.receiveMessage(builder -> builder
                .maxNumberOfMessages(10)
                .queueUrl(queueUrl))
                .messages();

        return messages.stream()
                .map(Message::body)
                .map(this::toQuark)
                .collect(Collectors.toList());
    }

    private Quark toQuark(String message) {
        Quark quark = null;
        try {
            quark = QUARK_READER.readValue(message);
        } catch (Exception e) {
            log.error("Failed to decode message",e);
            throw new RuntimeException(e);
        }
        return quark;
    }
}
