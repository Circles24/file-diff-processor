package com.circles24.JobsWorker.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
@Builder
public class JobResult {
    @Id
    @GeneratedValue(generator = "uuid2")
    private String id;

    private String jobId;

    private String resultFilePath;

    private FileFormat resultFileFormat;
}
