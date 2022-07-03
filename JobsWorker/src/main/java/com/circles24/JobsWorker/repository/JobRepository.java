package com.circles24.JobsWorker.repository;

import com.circles24.JobsWorker.domain.Job;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends CrudRepository<Job, String> {
    Optional<Job> findById(String id);
}
