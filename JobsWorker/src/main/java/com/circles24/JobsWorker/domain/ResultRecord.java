package com.circles24.JobsWorker.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ResultRecord {
    private Map<String, String> buyersRecord;

    private Map<String, String> suppliersRecord;

    private MatchType matchType;

    private double partialMatchDifference;

    public boolean isCompleteMatch() {
        return MatchType.COMPLETE.equals(this.matchType);
    }

    public boolean isPartialMatch() {
        return MatchType.PARTIAL.equals(this.matchType);
    }
}
