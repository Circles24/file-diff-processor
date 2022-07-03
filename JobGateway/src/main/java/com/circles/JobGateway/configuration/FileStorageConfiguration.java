package com.circles.JobGateway.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "file")
public class FileStorageConfiguration {
    private String uploadDir;
}
