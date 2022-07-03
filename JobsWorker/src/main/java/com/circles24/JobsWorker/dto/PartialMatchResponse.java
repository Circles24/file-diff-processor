package com.circles24.JobsWorker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PartialMatchResponse {
    private boolean partialMatch;

    private Double difference;
}
