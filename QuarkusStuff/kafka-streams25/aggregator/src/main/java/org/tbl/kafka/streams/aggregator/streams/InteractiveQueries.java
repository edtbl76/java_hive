package org.tbl.kafka.streams.aggregator.streams;

import lombok.extern.jbosslog.JBossLog;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyQueryMetadata;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.errors.InvalidStateStoreException;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.tbl.kafka.streams.aggregator.model.Aggregation;
import org.tbl.kafka.streams.aggregator.model.WeatherStationData;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.kafka.streams.KeyQueryMetadata.NOT_AVAILABLE;

@JBossLog
@ApplicationScoped
public class InteractiveQueries {

    @ConfigProperty(name = "hostname")
    String host;

    @Inject
    KafkaStreams streams;

    public List<PipelineMetadata> getMetadata() {
        return streams.allMetadataForStore(TopologyProducer.WEATHER_STATIONS_STORE)
                .stream()
                .map(mdata -> new PipelineMetadata(
                        mdata.hostInfo().host()+ ":" + mdata.hostInfo().port(),
                        mdata.topicPartitions()
                                .stream()
                                .map(TopicPartition::toString)
                                .collect(Collectors.toSet())
                )).collect(Collectors.toList());
    }

    public GetWeatherStationDataResult getWeatherStationData(int id) {
        KeyQueryMetadata metadata = streams.queryMetadataForKey(
                TopologyProducer.WEATHER_STATIONS_STORE, id, Serdes.Integer().serializer());

        if (metadata == null || metadata == NOT_AVAILABLE) {
            log.warnv("Metadata for key {0} not found.", id);
            return GetWeatherStationDataResult.notFound();
        } else if (metadata.getActiveHost().host().equals(host)) {
            log.infov("Metadata for key {0} found locally.", id);
            Aggregation result = getWeatherStationStore().get(id);

            if (result != null) {
                return GetWeatherStationDataResult.found(WeatherStationData.fromAggregation(result));
            } else {
                return GetWeatherStationDataResult.notFound();
            }
        } else {
            log.infov("Metadata for key {0} found on remote host {1}:{2}",
                    id, metadata.getActiveHost().host(), metadata.getActiveHost().port());
            return GetWeatherStationDataResult.foundRemotely(
                    metadata.getActiveHost().host(),
                    metadata.getActiveHost().port());
        }
    }

    private ReadOnlyKeyValueStore<Integer, Aggregation> getWeatherStationStore() {
        while (true) {
            try {
                StoreQueryParameters<ReadOnlyKeyValueStore<Integer, Aggregation>> params =
                        StoreQueryParameters
                                .fromNameAndType(TopologyProducer.WEATHER_STATIONS_STORE, QueryableStoreTypes.keyValueStore());
                /*
                    Just for note:

                    There is a version of store() that is deprecated that accepts a String and QueryableStoreTypes
                    as args, which allows us to completely bypass having to construct the StoreQueryParameters object.
                 */
                return streams.store(params);
            } catch (InvalidStateStoreException e) {
                // Store not ready. Probe that shit!
            }
        }
    }
}
