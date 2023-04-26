package org.tbl.microprofile.faulttolerance;

import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN_TYPE;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@JBossLog
@Path("/coffee")
public class CoffeeResource {

    @Inject
    CoffeeRepositoryService coffeeRepository;

    private AtomicLong counter = new AtomicLong(0);

    @GET
    // Adding retries prevents the maybeFail from causing more user-facing issues
    @Retry(maxRetries = 4)
    public List<Coffee> coffees() {
        final Long invocationNumber = counter.getAndIncrement();

        maybeFail(String.format("CoffeeResource#coffees() invocation# %d failed", invocationNumber));

        log.infof("CoffeeResource#coffees() invocation# %d returning successfully", invocationNumber);
        return coffeeRepository.listAll();
    }

    @GET
    @Path("{id}/recommend")
    @Timeout(250)
    // This will exec instead of timing out.
    @Fallback(fallbackMethod = "fallbackRecommend")
    public List<Coffee> recommend(@PathParam int id) {
        long started = System.currentTimeMillis();
        final long invocationNumber = counter.getAndIncrement();

        try {
            randomDelay();
            log.infof("CoffeeResource#recommend() invocation #%d returning successfully", invocationNumber);
            return coffeeRepository.recommend(id);
        } catch (InterruptedException e) {
            log.errorf("CoffeeResource#recommend() invocation #%d timed out after %d ms",
                    invocationNumber, System.currentTimeMillis() - started);
            return null;
        }
    }

    public List<Coffee> fallbackRecommend(int id) {
        log.info("Falling back to RecommendationResource#fallbackRecommand()");
        return Collections.singletonList(coffeeRepository.findById(1));
    }


    @Path("{id}/availability")
    @GET
    public Response availability(@PathParam int id) {
        final Long invocationNumber = counter.getAndIncrement();

        Coffee coffee = coffeeRepository.findById(id);
        // 404 if no coffee
        if (coffee == null){
            return Response.status(NOT_FOUND).build();
        }

        try {
            Integer availability = coffeeRepository.getAvailability(coffee);
            log.infof("CoffeeResource#availability() invocation #%d returning successfully", invocationNumber);
            return Response.ok(availability).build();
        } catch (RuntimeException e) {
            String message = e.getClass().getSimpleName() + ":" + e.getMessage();
            log.errorf("CoffeeResource#availability() invocation #%d failed: %s", invocationNumber, message);
            return Response.status(INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .type(TEXT_PLAIN_TYPE)
                    .build();
        }
    }

    /*
        Chaos Code
     */
    private void maybeFail(String failureLogMessage) {
        if (new Random().nextBoolean()) {
            log.error(failureLogMessage);
            throw new RuntimeException("Resource failure.");
        }
    }

    private void randomDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    }
}
