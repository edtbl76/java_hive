package org.tbl.kafka.streams.aggregator.rest;

import org.tbl.kafka.streams.aggregator.streams.GetWeatherStationDataResult;
import org.tbl.kafka.streams.aggregator.streams.InteractiveQueries;
import org.tbl.kafka.streams.aggregator.streams.PipelineMetadata;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@ApplicationScoped
@Path("/weather-stations")
public class WeatherStationEndpoint {

    @Inject
    InteractiveQueries queries;

    @GET
    @Path("data/{id}")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getWeatherStationData(@PathParam("id") int id) {
        GetWeatherStationDataResult result = queries.getWeatherStationData(id);

        if (result.getResult().isPresent()) {
            return Response.ok(result.getResult().get()).build();
        } else if (result.getHost().isPresent() && result.getPort().isPresent()) {
            URI other = getOtherUri(result.getHost().get(), result.getPort().getAsInt(), id);
            return Response.seeOther(other).build();
        } else {
            return Response.status(NOT_FOUND.getStatusCode(),
                    "No data found for weather station " + id).build();
        }
    }

    @GET
    @Path("meta-data")
    @Produces(APPLICATION_JSON)
    public List<PipelineMetadata> getMetadata() {
        return queries.getMetadata();
    }

    private URI getOtherUri(String host, int port, int id) {
        try {
            return new URI("http://" + host + ":" + port + "/weather-stations/data/" + id);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
