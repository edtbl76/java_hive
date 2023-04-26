package org.tbl.kafka.streams.aggregator.streams;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import io.quarkus.kafka.client.serialization.ObjectMapperSerializer;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.tbl.kafka.streams.aggregator.model.Aggregation;
import org.tbl.kafka.streams.aggregator.model.WeatherStation;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;
import static org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.tbl.kafka.streams.aggregator.streams.TopologyProducer.*;

@QuarkusTest
@QuarkusTestResource(KafkaResource.class)
public class AggregatorTest {

    static final String BROKER_LIST = "localhost:9092";

    KafkaProducer<Integer, String> temperatureProducer;
    KafkaProducer<Integer, WeatherStation> weatherStationsProducer;

    KafkaConsumer<Integer, Aggregation> weatherStationsConsumer;

    @BeforeEach
    void setUp() {
        temperatureProducer = new KafkaProducer<>(
                producerProperties(), new IntegerSerializer(), new StringSerializer());
        weatherStationsProducer = new KafkaProducer<>(
                producerProperties(), new IntegerSerializer(), new ObjectMapperSerializer<>());
        weatherStationsConsumer = new KafkaConsumer<>(
                consumerProperties(), new IntegerDeserializer(), new ObjectMapperDeserializer<>(Aggregation.class));
    }

    @AfterEach
    void tearDown() {
        temperatureProducer.close();
        weatherStationsProducer.close();
        weatherStationsConsumer.close();
    }

    @Test
    @Timeout(value = 30)
    public void test() {

        System.out.println("ENTERING TEST");
        weatherStationsConsumer
                .subscribe(Collections.singletonList(TEMPERATURES_AGGREGATED_TOPIC));

        weatherStationsProducer
                .send(new ProducerRecord<>(WEATHER_STATIONS_TOPIC, 1,
                        new WeatherStation(1, "Station1")));

        temperatureProducer
                .send(new ProducerRecord<>(TEMPERATURE_VALUES_TOPIC, 1, Instant.now() + ";" + "15"));
        temperatureProducer
                .send(new ProducerRecord<>(TEMPERATURE_VALUES_TOPIC, 1, Instant.now() + ";" + "25"));

        List<ConsumerRecord<Integer, Aggregation>> results = poll(weatherStationsConsumer, 1);

        // Make assertions
        assertEquals(2, results.get(0).value().getCount());
        assertEquals(1, results.get(0).value().getStationId());
        assertEquals("Station1", results.get(0).value().getStationName());
        assertEquals(20, results.get(0).value().getAverage());

    }

    private List<ConsumerRecord<Integer, Aggregation>> poll(
            Consumer<Integer, Aggregation> consumer, int expectedRecordCount) {

        int fetched = 0;
        List<ConsumerRecord<Integer, Aggregation>> result = new ArrayList<>();

        while (fetched < expectedRecordCount) {
            ConsumerRecords<Integer, Aggregation> records = consumer.poll(Duration.ofSeconds(1));
            records.forEach(result::add);
            fetched = result.size();
        }
        return result;
    }

    private Properties producerProperties() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        return properties;
    }

    private Properties consumerProperties() {
        Properties properties = new Properties();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(GROUP_ID_CONFIG, "test-group-id");
        properties.put(ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }
}
