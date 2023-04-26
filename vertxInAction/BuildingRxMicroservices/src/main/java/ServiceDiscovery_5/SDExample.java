package ServiceDiscovery_5;

import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.client.WebClient;
import io.vertx.reactivex.servicediscovery.ServiceDiscovery;
import io.vertx.reactivex.servicediscovery.ServiceReference;
import io.vertx.reactivex.servicediscovery.types.EventBusService;
import io.vertx.reactivex.servicediscovery.types.HttpEndpoint;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;

import java.util.Arrays;
import java.util.List;

public class SDExample extends AbstractVerticle {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(SDExample.class.getName());
    }

    @Override
    public void start() {
        ServiceDiscovery discovery = ServiceDiscovery.create(vertx,
                new ServiceDiscoveryOptions()
                .setAnnounceAddress("service-announcement")
                .setName("my-service-name"));

        // Custom Record
        Record customRecord = new Record()
                .setType("eventbus-service-proxy")
                .setLocation(new JsonObject().put("endpoint1", "service-address-1"))
                .setName("my-service-1")
                .setMetadata(new JsonObject().put("some-label", "some-value"));

        // Create Records from type
        Record restRecord = HttpEndpoint.createRecord("well-rested-api", "localhost", 8080, "/restapi");
        Record busRecord = EventBusService.createRecord("dodged-a-bus", "some-eventbus", SDExample.class.getName());

        // Publish!
        List<Record> records = Arrays.asList(customRecord, restRecord, busRecord);
        records.forEach(record -> discovery.publish(record, recordAsyncResult -> {
            if (recordAsyncResult.succeeded())
                System.out.println(record.getName() + " published");
            else
                System.out.println("Failed to publish " + record.getName());
        }));

        // Unpublish one for kicks
        discovery.unpublish(customRecord.getRegistration(), voidAsyncResult -> {
            if (voidAsyncResult.succeeded())
                System.out.println(customRecord.getName() + " unpublished");
            else {
                System.out.println("Couldn't unpublish " + customRecord.getName()
                        + ". It may already have been removed, or the record was not published.");
            }
        });

        // CONSUME!
        discovery.getRecord(record -> record.getName().equals(restRecord.getName()), recordAsyncResult -> {
            if (recordAsyncResult.succeeded() && recordAsyncResult.result() != null) {
                ServiceReference reference = discovery.getReference(recordAsyncResult.result());
                // get its object
                WebClient client = reference.getAs(WebClient.class);

                client.get("/restapi").send(response -> {
                    System.out.println("Consuming " + restRecord.getName());
                    reference.release();
                });
            }
        });


        discovery.close();
    }
}
