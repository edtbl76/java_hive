package org.tbl.kafka.streams.aggregator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class TemperatureMeasurement {

    private int stationId;
    private String stationName;
    private Instant timestamp;
    private double value;

}
