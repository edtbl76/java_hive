package org.tbl.kafka.streams.aggregator.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

import java.math.BigDecimal;

import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MIN_VALUE;
import static java.math.RoundingMode.HALF_UP;

@Data
@RegisterForReflection
public class Aggregation {

    private int stationId;
    private String stationName;
    private double min = MAX_VALUE;
    private double max = MIN_VALUE;
    private int count;
    private double sum;
    private double average;

    public Aggregation updateFrom(TemperatureMeasurement measurement) {
        double value = measurement.getValue();
        stationId = measurement.getStationId();
        stationName = measurement.getStationName();

        count++;
        sum += value;

        average = BigDecimal.valueOf(sum/count)
                .setScale(1, HALF_UP).doubleValue();

        min = Math.min(min, value);
        max = Math.max(max, value);

        return this;
    }


}
