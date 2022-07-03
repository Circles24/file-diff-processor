package com.circles.JobGateway.exception;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Map;

@Data
@Builder
public class AppException extends RuntimeException {
    private String name;

    private String message;

    private long statusCode;

    private long errorCode;

    private Map<String, Object> context;

    private Timestamp timestamp;
}
