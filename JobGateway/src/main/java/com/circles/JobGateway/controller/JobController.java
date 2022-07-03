package com.circles.JobGateway.controller;

import com.circles.JobGateway.domain.FileFormat;
import com.circles.JobGateway.domain.FileType;
import com.circles.JobGateway.domain.Job;
import com.circles.JobGateway.dto.FileUploadRequest;
import com.circles.JobGateway.exception.AppException;
import com.circles.JobGateway.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    @Autowired
    private final JobService jobService;

    @GetMapping("/{jobId}")
    @ExceptionHandler({ AppException.class })
    public Job getJobById(@PathVariable String jobId) {
        return jobService.getJobById(jobId);
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.create(job);
    }

    @PatchMapping
    public Job updateJob(@RequestBody Job job) {
        return jobService.update(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping(path = "/{job_id}/files/{file_type}")
    public Job uploadFile(@PathParam("job_id") String jobId, @PathParam("file_type") FileType fileType,
                           @RequestParam FileFormat fileFormat, @RequestParam MultipartFile file) {
        FileUploadRequest request = FileUploadRequest.builder().jobId(jobId).fileType(fileType).fileFormat(fileFormat)
                .file(file).build();
        return jobService.uploadFile(request);
    }
}
