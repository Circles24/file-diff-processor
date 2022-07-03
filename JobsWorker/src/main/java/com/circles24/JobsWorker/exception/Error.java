package com.circles24.JobsWorker.exception;

import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Objects;

@RequiredArgsConstructor
public enum Error {
    JOB_NOT_FOUND(AppException.builder().message("job not found").statusCode(404).errorCode(1001).build()),
    INVALID_JOB(AppException.builder().message("invalid job").statusCode(400).errorCode(1002).build()),
    RESULT_NOT_FOUND(AppException.builder().message("result not found").statusCode(404).errorCode(1003).build()),
    INTERNAL_SERVER_ERROR(AppException.builder().message("internal server error").statusCode(500).errorCode(1004).build()),
    INVALID_FILE_PATH(AppException.builder().message("invalid file path").statusCode(400).errorCode(1005).build()),
    INVALID_FILE(AppException.builder().message("invalid file").statusCode(400).errorCode(1006).build()),
    METHOD_NOT_IMPLEMENTED(AppException.builder().message("method not implemented").statusCode(500).errorCode(1007).build()),
    JOB_NOT_IN_PROCESSING_REQUESTED_STATE(AppException.builder().message("job not in processing requested state").statusCode(400).errorCode(1008).build());

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
