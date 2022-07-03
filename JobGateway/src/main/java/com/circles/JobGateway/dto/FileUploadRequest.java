package com.circles.JobGateway.dto;

import com.circles.JobGateway.domain.FileFormat;
import com.circles.JobGateway.domain.FileType;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class FileUploadRequest {
    private String jobId;

    private FileType fileType;

    private FileFormat fileFormat;

    private MultipartFile file;
}
