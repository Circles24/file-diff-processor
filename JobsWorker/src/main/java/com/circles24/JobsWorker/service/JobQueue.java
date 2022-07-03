package com.circles24.JobsWorker.service;

import com.circles24.JobsWorker.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
@RequiredArgsConstructor
public class JobQueue {
    private final Queue<Event> queue = new LinkedBlockingQueue<>();

    public void push(Event event) {
        queue.add(event);
    }

    public boolean hasEvent() {
        return !queue.isEmpty();
    }

    public Event pop() {
        return queue.poll();
    }
}
