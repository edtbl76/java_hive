package org.tbl.microprofile.metrics;

import io.micrometer.core.instrument.MeterRegistry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.Supplier;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Path("/")
public class PrimeNumberResource {

    private final LongAccumulator highest = new LongAccumulator(Long::max, 0);
    private final MeterRegistry registry;

    PrimeNumberResource(MeterRegistry registry) {
        this.registry = registry;

        /*
            Create a gauge that uses the "highestCurrent" method to get
            the highest observed prime
         */
        registry.gauge("prime.number.max", this, PrimeNumberResource::highestObservedPrime);
    }

    @GET
    @Path("{number}")
    @Produces(TEXT_PLAIN)
    public String checkIfPrime(@PathParam("number") long number) {
        if (number < 1) {
            return "Only natural numbers can be primes";
        }

        if (number == 1) {
            return "1 is not prime";
        }

        if (number == 2) {
            return "2 is prime";
        }

        if (number % 2 == 0) {
            return number + " is not prime. (Multiple of 2)";
        }

        /*
            Do you like my prime algo :P
         */
        Supplier<String> supplier = () -> {
            for (int i = 3; i < Math.floor(Math.sqrt(number)) + 1; i = i + 2) {
                if (number % i == 0) {
                    return number + " is not prime. (Multiple of " + i + ")";
                }
            }
            highest.accumulate(number);
            return number + " is prime.";
        };

        return registry.timer("prime.number.test")
                .wrap(supplier)
                .get();
    }

    long highestObservedPrime() {
        return highest.get();
    }
}
