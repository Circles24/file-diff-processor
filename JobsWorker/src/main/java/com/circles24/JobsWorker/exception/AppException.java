package com.circles24.JobsWorker.exception;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashMap;

@Data
@Builder
public class AppException extends RuntimeException {
    private String name;

    private String message;

    private long statusCode;

    private long errorCode;

    private HashMap<String, Object> context;

    private Timestamp timestamp;
}
