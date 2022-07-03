package com.circles24.JobsWorker.domain;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;

@Data
@Builder
public class Event {
    private String id;

    private String eventType;

    private HashMap<String, Object> data;
}
