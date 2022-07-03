package com.circles24.JobsWorker.domain;

import com.circles24.JobsWorker.configuration.MatcherConfiguration;
import com.circles24.JobsWorker.dto.PartialMatchResponse;
import com.circles24.JobsWorker.util.StringSimilarity;

import java.time.Instant;
import java.util.Objects;

public enum DataType {
    STRING {
        @Override
        public PartialMatchResponse partialMatch(String d1, String d2, MatcherConfiguration configuration) {
            int hammingDistance = StringSimilarity.hammingDistance(d1, d2);
            return PartialMatchResponse.builder()
                    .partialMatch(hammingDistance <= configuration.getMaxAllowedHammingDistance())
                    .difference((double) hammingDistance).build();
        }
    },
    NUMBER {
        @Override
        public PartialMatchResponse partialMatch(String d1, String d2, MatcherConfiguration configuration) {
            Double num1 = Double.valueOf(d1);
            Double num2 = Double.valueOf(d2);
            double difference = Math.abs(num1-num2);
            return PartialMatchResponse.builder()
                    .partialMatch(difference <= configuration.getMaxAllowedNumberDifference())
                    .difference(difference).build();
        }
    },
    DATE {
        @Override
        public PartialMatchResponse partialMatch(String d1, String d2, MatcherConfiguration configuration) {
            long t1 = Instant.parse(d1).getEpochSecond();
            long t2 = Instant.parse(d2).getEpochSecond();
            double difference = t1-t2;
            return PartialMatchResponse.builder()
                    .partialMatch(difference <= configuration.getMaxAllowedTimestampDiffInMillis())
                    .difference(difference).build();
        }
    };

    public boolean completeMatch(String d1, String d2) {
        return Objects.equals(d1, d2);
    }

    public abstract PartialMatchResponse partialMatch(String d1, String d2, MatcherConfiguration configuration);
}
