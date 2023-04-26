package org.tbl.kafka.streams.producer.generator;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.Record;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.jbosslog.JBossLog;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.math.RoundingMode.*;
import static java.time.Duration.ofMillis;

/*
    Bean that generates random temp values per second
    - Written to Kafka topic (temperature-values)
    - Additional topic holds weather stations (weather-stations)

 */
@JBossLog
@ApplicationScoped
public class ValuesGenerator {

    private Random random = new Random();

    private List<WeatherStation> stations = Collections.unmodifiableList(
            Arrays.asList(
                    new WeatherStation(1, "Copenhagen", 9),
                    new WeatherStation(2, "Helsinki", 6),
                    new WeatherStation(3, "Reykjavik", 4),
                    new WeatherStation(4, "Oslo", 6),
                    new WeatherStation(5, "Svalbard", -7),
                    new WeatherStation(6, "Stockholm", 7),
                    new WeatherStation(7,"Amsterdam", 10),
                    new WeatherStation(8, "Murmansk", 0),
                    new WeatherStation(9, "Gjoa Haven", -14)));

    @Outgoing("temperature-values")
    public Multi<Record<Integer, String>> generate() {
        return Multi.createFrom()
                .ticks().every(ofMillis(500))
                .onOverflow().drop()
                .map(tick -> {
                    WeatherStation station = stations.get(random.nextInt(stations.size()));
                    double temperature = BigDecimal.valueOf(random.nextGaussian() * 15 + station.averageTemperature)
                            .setScale(1, HALF_UP)
                            .doubleValue();

                    log.infov("station: {0}, temperature {1}", station.name, temperature);
                    return Record.of(station.getId(), Instant.now() + ";" + temperature);
                });
    }

    @Outgoing("weather-stations")
    public Multi<Record<Integer, String>> weatherStations() {
        return Multi.createFrom().items(stations.stream()
                        .map(station -> Record.of(station.id,
                                "{ \"id\" : " + station.id + ", \"name\" : \"" + station.name + "\" }"))
                );
    }

    @Data
    @AllArgsConstructor
    public static class WeatherStation {

        int id;
        String name;
        int averageTemperature;
    }
}
