package com.circles24.JobsWorker.service;

import com.circles24.JobsWorker.configuration.EventProcessorConfiguration;
import com.circles24.JobsWorker.domain.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobConsumer implements Runnable {
    private final JobQueue queue;

    private final EventProcessor processor;

    private final EventProcessorConfiguration configuration;

    @Bean
    public JobConsumer init(JobQueue queue, EventProcessor processor, EventProcessorConfiguration configuration) {
        JobConsumer consumer = new JobConsumer(queue, processor, configuration);
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(consumer);
        return consumer;
    }

    @Override
    public void run() {
        while(true) {
            try {
                if (queue.hasEvent()) {
                    Event event = queue.pop();
                    processor.process(event);
                }
            } catch (Exception ex) {
                log.error("exception while processing event", ex);
            } finally {
                try {
                    Thread.sleep(configuration.getThreadSleepTime());
                } catch(Exception ignored) {}
            }
        }
    }
}
