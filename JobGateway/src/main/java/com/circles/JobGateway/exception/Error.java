package com.circles.JobGateway.exception;

import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;

@RequiredArgsConstructor
public enum Error {
    JOB_NOT_FOUND(AppException.builder().message("job not found").statusCode(404).build()),
    INVALID_JOB(AppException.builder().message("invalid job").statusCode(400).build()),
    RESULT_NOT_FOUND(AppException.builder().message("result not found").statusCode(404).build()),
    INTERNAL_SERVER_ERROR(AppException.builder().message("internal server error").statusCode(500).build());

    private final AppException exception;

    public AppException build() {
        exception.setName(this.name());
        if (Objects.isNull(exception.getContext())) {
            HashMap<String, Object> context = new HashMap<>();
            context.put("source", "JobGateway");
            exception.setContext(context);
        }
        exception.setTimestamp(new Timestamp(System.currentTimeMillis()));
        return exception;
    }
}
