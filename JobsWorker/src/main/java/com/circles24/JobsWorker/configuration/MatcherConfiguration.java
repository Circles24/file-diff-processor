package com.circles24.JobsWorker.configuration;

import com.circles24.JobsWorker.domain.DataType;
import com.circles24.JobsWorker.domain.MatcherType;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;

@Data
@Configuration
public class MatcherConfiguration {
    private List<String> matchingKeys;

    private HashMap<String, DataType> dataTypeMap;

    private int maxAllowedHammingDistance;

    private double maxAllowedNumberDifference;

    private long maxAllowedTimestampDiffInMillis;

    private String dateFormat;

    private MatcherType matcherType;
}
