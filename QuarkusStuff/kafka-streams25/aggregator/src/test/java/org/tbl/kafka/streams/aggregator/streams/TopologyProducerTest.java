package org.tbl.kafka.streams.aggregator.streams;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import io.quarkus.kafka.client.serialization.ObjectMapperSerializer;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.test.TestRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tbl.kafka.streams.aggregator.model.Aggregation;
import org.tbl.kafka.streams.aggregator.model.WeatherStation;

import javax.inject.Inject;
import java.time.Instant;
import java.util.Properties;

import static org.apache.kafka.streams.StreamsConfig.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.tbl.kafka.streams.aggregator.streams.TopologyProducer.*;

@QuarkusTest
public class TopologyProducerTest {

    @Inject
    Topology topology;

    TopologyTestDriver testDriver;

    TestInputTopic<Integer, String> temperatures;
    TestInputTopic<Integer, WeatherStation> weatherStations;

    TestOutputTopic<Integer, Aggregation> aggregations;

    @BeforeEach
    void setup() {
        Properties properties = new Properties();
        properties.put(APPLICATION_ID_CONFIG, "testApplicationid");
        properties.put(BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
        testDriver = new TopologyTestDriver(topology, properties);

        temperatures = testDriver.createInputTopic(
                TEMPERATURE_VALUES_TOPIC, new IntegerSerializer(), new StringSerializer());

        weatherStations = testDriver.createInputTopic(
                WEATHER_STATIONS_TOPIC, new IntegerSerializer(), new ObjectMapperSerializer<>());

        aggregations = testDriver.createOutputTopic(
                TEMPERATURES_AGGREGATED_TOPIC, new IntegerDeserializer(),
                new ObjectMapperDeserializer<>(Aggregation.class)
        );
    }

    @AfterEach
    void teardown() {
        testDriver.getTimestampedKeyValueStore(WEATHER_STATIONS_STORE).flush();
        testDriver.close();
    }

    @Test
    void test() {
        WeatherStation station = new WeatherStation(1, "Station1");
        weatherStations.pipeInput(station.getId(), station);

        temperatures.pipeInput(station.getId(), Instant.now() + ";" + "15");
        temperatures.pipeInput(station.getId(), Instant.now() + ";" + "25");

        aggregations.readRecord();
        TestRecord<Integer, Aggregation> result = aggregations.readRecord();

        assertEquals(2, result.getValue().getCount());
        assertEquals(1, result.getValue().getStationId());
        assertEquals("Station1", result.getValue().getStationName());
        assertEquals(20, result.getValue().getAverage());
    }
}
