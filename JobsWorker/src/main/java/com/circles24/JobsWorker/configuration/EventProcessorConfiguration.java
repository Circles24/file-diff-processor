package com.circles24.JobsWorker.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class EventProcessorConfiguration {
    private long threadSleepTime;
}
