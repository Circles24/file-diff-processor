package com.circles.JobGateway.service;

import com.circles.JobGateway.domain.JobResult;
import com.circles.JobGateway.exception.Error;
import com.circles.JobGateway.repository.JobResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultService {
    @Autowired
    private final JobResultRepository jobResultRepository;

    public JobResult getResultById(final String id) {
        return jobResultRepository.findById(id).orElseThrow(Error.RESULT_NOT_FOUND::build);
    }

    public JobResult getResultByJobId(final String jobId) {
        return jobResultRepository.findByJobId(jobId).orElseThrow(Error.RESULT_NOT_FOUND::build);
    }
}
