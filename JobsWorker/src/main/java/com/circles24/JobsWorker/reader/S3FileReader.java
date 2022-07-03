package com.circles24.JobsWorker.reader;

import com.circles24.JobsWorker.exception.Error;

import java.io.InputStream;

public class S3FileReader implements FileReader {
    @Override
    public InputStream readFile(String filePath) {
        throw Error.METHOD_NOT_IMPLEMENTED.build();
    }
}
