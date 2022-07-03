package com.circles24.JobsWorker.controller;

import com.circles24.JobsWorker.domain.Event;
import com.circles24.JobsWorker.service.JobQueue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private final JobQueue queue;

    @PostMapping
    @RequestMapping("/job")
    public void submitJob(@RequestBody Event event) {
        queue.push(event);
    }
}
