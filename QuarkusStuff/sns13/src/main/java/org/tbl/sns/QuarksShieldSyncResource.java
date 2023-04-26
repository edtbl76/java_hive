package org.tbl.sns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.tbl.sns.model.Quark;
import org.tbl.sns.model.SnsNotification;
import org.tbl.sns.model.SnsSubscriptionConfirmation;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@JBossLog
@Path("/sync/shield")
public class QuarksShieldSyncResource {

    private static final String NOTIFICATION_TYPE = "Notification";
    private static final String SUBSCRIPTION_CONFIRMATION_TYPE = "SubscriptionConfirmation";
    private static final String UNSUBSCRIPTION_CONFIRMATION_TYPE = "UnsubscribeConfirmation";

    @Inject
    SnsClient client;

    @ConfigProperty(name = "topic.arn")
    String topicArn;

    @ConfigProperty(name = "quarks.shield.base.url")
    String quarksShieldBaseUrl;

    private volatile String subscriptionArn;

    static Map<Class<?>, ObjectReader> READERS = new HashMap<>();

    static {
        READERS.put(SnsNotification.class, new ObjectMapper().readerFor(SnsNotification.class));
        READERS.put(SnsSubscriptionConfirmation.class, new ObjectMapper().readerFor(SnsSubscriptionConfirmation.class));
        READERS.put(Quark.class, new ObjectMapper().readerFor(Quark.class));
    }

    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    public Response notificationEndpoint(@HeaderParam("x-amz-sns-message-type") String messageType, String message)
            throws JsonProcessingException {
        if (messageType == null) {
            return Response.status(400).build();
        }

        switch (messageType) {
            case NOTIFICATION_TYPE:
                SnsNotification notification = readObject(SnsNotification.class, message);
                Quark quark = readObject(Quark.class, notification.getMessage());
                log.infov("Quark[{0}, {1}] collision with the shield", quark.getFlavor(), quark.getSpin());
                break;
            case SUBSCRIPTION_CONFIRMATION_TYPE:
                SnsSubscriptionConfirmation confirmation = readObject(SnsSubscriptionConfirmation.class, message);
                client.confirmSubscription(builder -> builder.topicArn(topicArn).token(confirmation.getToken()));
                log.infov("Subscription confirmed. Ready for quarks collisons");
                break;
            case UNSUBSCRIPTION_CONFIRMATION_TYPE:
                log.info("Unsubscribed");
                break;
            default:
                return Response.status(400).entity("Unknown messageType").build();
        }

        return Response.ok().build();
    }

    private String notificationEndpoint() {
        return quarksShieldBaseUrl + "/sync/shield";
    }

    @POST
    @Path("subscribe")
    public Response subscribe() {
        String notificationEndpoint = notificationEndpoint();
        SubscribeResponse response = client.subscribe(
                builder -> builder.topicArn(topicArn).protocol("http").endpoint(notificationEndpoint));
        subscriptionArn = response.subscriptionArn();
        log.infov("Subscribed Quarks shield <{0}> : {1}", notificationEndpoint, response.subscriptionArn());
        return Response.ok().entity(response.subscriptionArn()).build();
    }

    @POST
    @Path("unsubscribe")
    public Response unsubscribe() {
        if (subscriptionArn != null) {
            client.unsubscribe(builder -> builder.subscriptionArn(subscriptionArn));
            log.infov("Unsubscribed Quarks shield for id = {0}", subscriptionArn);
            return Response.ok().build();
        } else {
            log.info("Not Subscribed");
            return Response.status(400).entity("Not subscribed yet").build();
        }
    }

    private <T> T readObject(Class<T> clazz, String message) {
        T object = null;
        try {
            object = READERS.get(clazz).readValue(message);
        } catch (JsonProcessingException e) {
            log.errorv("Unable to deserialize message <{0}> to Class <{1}>", message, clazz.getSimpleName());
            throw new RuntimeException(e);
        }
        return object;
    }
}
