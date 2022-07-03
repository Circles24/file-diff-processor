package com.circles24.JobsWorker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
class ControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AppException.class)
    public @ResponseBody ResponseEntity<AppException> handleAppException(AppException ex) {
        return new ResponseEntity<>(ex, HttpStatus.resolve((int)ex.getStatusCode()));
    }

    // fallback method
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppException> handleExceptions(Exception e) {
        return new ResponseEntity<>(
                Error.INTERNAL_SERVER_ERROR.build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
