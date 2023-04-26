package org.tbl.kafka.streams.aggregator.streams;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class PipelineMetadata {

    public String host;
    public Set<String> partitions;
}
