package com.circles.JobGateway.controller;

import com.circles.JobGateway.domain.JobResult;
import com.circles.JobGateway.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/results")
public class ResultController {

    @Autowired
    private final ResultService resultService;

    @GetMapping
    @RequestMapping(path = "/{id}")
    public JobResult getById(@PathVariable("id") final String id) {
        return resultService.getResultById(id);
    }

    @GetMapping
    @RequestMapping(path = "/job/{job_id}")
    public JobResult getByJobId(@PathVariable("job_id") final String jobId) {
        return resultService.getResultByJobId(jobId);
    }
}
