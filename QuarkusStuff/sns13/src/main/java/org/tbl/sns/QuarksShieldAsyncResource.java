package org.tbl.sns;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.smallrye.mutiny.Uni;
import lombok.Setter;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.tbl.sns.model.Quark;
import org.tbl.sns.model.SnsNotification;
import org.tbl.sns.model.SnsSubscriptionConfirmation;
import software.amazon.awssdk.services.sns.SnsAsyncClient;
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
@Path("/async/shield")
public class QuarksShieldAsyncResource {

    private static final String NOTIFICATION_TYPE = "Notification";
    private static final String SUBSCRIPTION_CONFIRMATION_TYPE = "SubscriptionConfirmation";
    private static final String UNSUBSCRIPTION_CONFIRMATION_TYPE = "UnsubscribeConfirmation";

    @Inject
    SnsAsyncClient client;

    @ConfigProperty(name = "topic.arn")
    String topicArn;

    @ConfigProperty(name = "quarks.shield.base.url")
    String quarksShieldBaseUrl;

    @Setter
    private volatile String subscriptionArn;

    static Map<Class<?>, ObjectReader> READERS = new HashMap<>();

    static {
        READERS.put(SnsNotification.class, new ObjectMapper().readerFor(SnsNotification.class));
        READERS.put(SnsSubscriptionConfirmation.class, new ObjectMapper().readerFor(SnsSubscriptionConfirmation.class));
        READERS.put(Quark.class, new ObjectMapper().readerFor(Quark.class));
    }

    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    public Uni<Response> notificationEndpoint(
            @HeaderParam("x-amz-sns-message-type") String messageType, String message) {

        if (messageType == null) {
            return Uni.createFrom().item(Response.status(400).build());
        }

        switch (messageType) {
            case NOTIFICATION_TYPE:
                return Uni.createFrom().item(readObject(SnsNotification.class, message))
                        .onItem().transform(snsNotification -> readObject(Quark.class, snsNotification.getMessage()))
                        .onItem().invoke(quark ->
                                log.infov("Quark[{0}, {1}] collision with the shield.",
                                        quark.getFlavor(), quark.getSpin()))
                        .onItem().transform(quark -> Response.ok().build());
            case SUBSCRIPTION_CONFIRMATION_TYPE:
                return Uni.createFrom().item(readObject(SnsSubscriptionConfirmation.class, message))
                        .onItem().transformToUni(confirmation ->
                                Uni.createFrom()
                                        .completionStage(client.confirmSubscription(builder ->
                                                builder.topicArn(topicArn).token(confirmation.getToken()))))
                        .onItem().invoke(response ->
                                log.infov("Subscribtion confirmed. Ready for quarks collisions"))
                        .onItem().transform(confirmSubscriptionResponse -> Response.ok().build());
            case UNSUBSCRIPTION_CONFIRMATION_TYPE:
                log.info("Unsubscribed");
                return Uni.createFrom().item(Response.ok().build());
        }

        return Uni.createFrom().item(Response.status(400).entity("Unknown messageType").build());
    }

    private String notificationEndpoint() {
        return quarksShieldBaseUrl + "/async/shield";
    }

    @POST
    @Path("subscribe")
    public Uni<Response> subscribe() {
        return Uni.createFrom().completionStage(client.subscribe(
                        builder -> builder.topicArn(topicArn).protocol("http").endpoint(notificationEndpoint())))
                .onItem().transform(SubscribeResponse::subscriptionArn)
                .onItem().invoke(this::setSubscriptionArn)
                .onItem().invoke(arn -> log.infov("Subscribed Quarks shield with id {0}", arn))
                .onItem().transform(s -> Response.ok().entity(s).build());
    }


    @POST
    @Path("unsubscribe")
    public Uni<Response> unsubscribe() {
        if (subscriptionArn != null) {
            return Uni.createFrom()
                    .completionStage(client.unsubscribe(builder -> builder.subscriptionArn(subscriptionArn)))
                    .onItem().invoke(response ->
                            log.infov("Unsubscribed quarks shield for id {0}", subscriptionArn))
                    .onItem().invoke(response -> subscriptionArn = null)
                    .onItem().transform(response -> Response.ok().build());
        } else {
            return Uni.createFrom().item(Response.status(400).entity("Not subscribed").build());
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
