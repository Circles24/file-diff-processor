package com.circles.JobGateway.repository;

import com.circles.JobGateway.domain.JobResult;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JobResultRepository extends CrudRepository<JobResult, String> {
    Optional<JobResult> findByJobId(String jobId);
}
