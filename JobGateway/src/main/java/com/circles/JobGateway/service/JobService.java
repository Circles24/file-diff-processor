package com.circles.JobGateway.service;

import com.circles.JobGateway.domain.Job;
import com.circles.JobGateway.domain.JobState;
import com.circles.JobGateway.dto.FileUploadRequest;
import com.circles.JobGateway.exception.Error;
import com.circles.JobGateway.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    @Autowired
    private final JobRepository jobRepository;

    @Autowired
    private final FileUploadService fileUploadService;

    private static final String FILE_NAME_FORMAT = "%s:%s.%s";

    public Job getJobById(String jobId) {
        return jobRepository.findById(jobId).orElseThrow(Error.JOB_NOT_FOUND::build);
    }

    public Job create(Job job) {
        return jobRepository.save(job);
    }

    public Job update(Job job) {
        if (StringUtils.isBlank(job.getId())) {
            throw Error.INVALID_JOB.build();
        }

        Job persistedJob = jobRepository.findById(job.getId()).orElseThrow(Error.JOB_NOT_FOUND::build);
        persistedJob.merge(job);
        return jobRepository.save(persistedJob);
    }

    public List<Job> getAllJobs() {
        return IterableUtils.toList(jobRepository.findAll());
    }

    public Job uploadFile(FileUploadRequest request) {
        Job job = getJobById(request.getJobId());
        String fileName = String.format(FILE_NAME_FORMAT, request.getJobId(), request.getFileType(), request.getFileFormat());
        String filePath = fileUploadService.uploadFile(request.getFile(), fileName);
        request.getFileType().setUploadFilePath(job, filePath);
        if (StringUtils.isNotBlank(job.getBuyersFilePath()) && StringUtils.isNotBlank(job.getSuppliersFilePath())) {
            job.setState(JobState.FILES_UPLOADED);
        }
        return jobRepository.save(job);
    }
}
