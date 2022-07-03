package com.circles24.JobsWorker.domain;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@Builder
public class Job {
    @Id
    @GeneratedValue(generator = "uuid2")
    private String id;

    private String owner;

    private String ownerEmail;

    private String buyersFilePath;

    private FileFormat buyersFileFormat;

    private FileSource buyersFileSource;

    private String suppliersFilePath;

    private FileFormat suppliersFileFormat;

    private FileSource suppliersFileSource;

    private FileFormat resultFormat;

    private JobState state;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public void merge(Job job) {
        if (StringUtils.isNotBlank(job.getOwner())) {
            this.owner = job.getOwner();
        }

        if (StringUtils.isNotBlank(job.getOwnerEmail())) {
            this.owner = job.getOwner();
        }

        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
