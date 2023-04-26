package org.tbl.kafka.streams.aggregator.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;

@Data
@AllArgsConstructor
@RegisterForReflection
public class WeatherStationData {

    private int stationId;
    private String stationName;
    private double min = MAX_VALUE;
    private double max = MIN_VALUE;
    private int count;
    private double average;

    public static WeatherStationData fromAggregation(Aggregation aggregation) {
        return new WeatherStationData(
                aggregation.getStationId(),
                aggregation.getStationName(),
                aggregation.getMin(),
                aggregation.getMax(),
                aggregation.getCount(),
                aggregation.getAverage()
        );
    }
}
